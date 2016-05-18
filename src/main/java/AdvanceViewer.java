import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import sun.reflect.misc.MethodUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2016/5/19.
 */
public class AdvanceViewer {

    private static String getMethodName(String propertyName) {
        return "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    private static String setMethodName(String propertyName) {
        return "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }

    public static void supportSort(final TableViewer tableViewer, final Object model, final String propertyName, Map<String, String> columnToPropertyMap) {
        tableViewer.setData("sort", true);
        TableColumn[] columns = tableViewer.getTable().getColumns();
        for (TableColumn column : columns) {
            String text = column.getText();
            String releationName = columnToPropertyMap.get(text);
            if (releationName == null) {
                throw new RuntimeException("未找到列" + text + "对应的属性名称");
            }
            column.setData("propertyName", releationName);
            column.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event event) {
                    try {
                        TableColumn tc = (TableColumn) event.widget;
                        Method method = MethodUtil.getMethod(model.getClass(), getMethodName(propertyName), null);
                        List invoke = (List) method.invoke(model);
                        final String propertyName1 = (String) tc.getData("propertyName");
                        Collections.sort(invoke, new Comparator<Object>() {
                            public int compare(Object o1, Object o2) {
                                try {
                                    Method method = o1.getClass().getMethod("get" + propertyName1.substring(0, 1).toUpperCase() + propertyName1.substring(1));
                                    Class<?> returnType = method.getReturnType();
                                    int result = 0;
                                    if (returnType == String.class) {
                                        result = ((String) method.invoke(o1)).compareTo(((String) method.invoke(o2)));
                                    } else if (returnType == Integer.class) {
                                        result = ((Integer) method.invoke(o1)).compareTo(((Integer) method.invoke(o2)));
                                    } else if (returnType == Date.class) {
                                        result = ((Date) method.invoke(o1)).compareTo(((Date) method.invoke(o2)));
                                    } else if (returnType == Enum.class) {
                                        result = ((Enum) method.invoke(o1)).name().compareTo(((Enum) method.invoke(o2)).name());
                                    }
                                    if ((Boolean) tableViewer.getData("sort") == false) {
                                        return result * 1;
                                    } else {
                                        return result * -1;
                                    }
                                } catch (Exception e) {
                                    return 0;
                                }
                            }
                        });
                        Method setMethod = MethodUtil.getMethod(model.getClass(), setMethodName(propertyName), new Class[]{List.class});
                        ArrayList<Object> objects = new ArrayList<Object>();
                        objects.addAll(invoke);
                        invoke.clear();
                        setMethod.invoke(model, invoke);
                        setMethod.invoke(model, objects);
                        tableViewer.setData("sort", !(Boolean) tableViewer.getData("sort"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    public static void supportSort(TableViewer tableViewer, Object model, String propertyName, String[] columnNames, String[] propertyNames) {
        if (columnNames.length != propertyNames.length) {
            throw new RuntimeException("表格列数量与对应绑定属性数量不相符");
        }
        HashMap<String, String> objectObjectHashMap = new HashMap<String, String>();
        for (int i = 0; i < columnNames.length; i++) {
            objectObjectHashMap.put(columnNames[i], propertyNames[i]);
        }
        supportSort(tableViewer, model, propertyName, objectObjectHashMap);
    }
}
