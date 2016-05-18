import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;
import sun.plugin2.util.PojoUtil;

/**
 * Created by Administrator on 2016/5/17.
 */
public class Main {

    private Model model = new Model();
    private TableViewer tableViewer;

    public static void main(String[] args) {
        Main main = new Main();
        main.open();
    }

    public void open(){
        Display display = new Display();
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        createContent(shell);
        shell.open();
        while (!shell.isDisposed()) {
            if(!display.readAndDispatch()){
                display.sleep();
            }
        }
        display.dispose();
    }

    private void createContent(Shell shell){
        shell.setLayout(new FillLayout());

        this.tableViewer = new TableViewer(shell, SWT.FULL_SELECTION);
        tableViewer.setLabelProvider(new LabelProvider());
        tableViewer.setContentProvider(new ArrayContentProvider());
        tableViewer.getTable().setLinesVisible(true);
        tableViewer.getTable().setHeaderVisible(true);

        for (int i = 0; i < 10; i++) {
            TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
            tableViewerColumn.getColumn().setText(i + "");
            tableViewerColumn.getColumn().setWidth(100);
        }
        bind();
        for (int i = 0; i < 500; i++) {
            Bean bean = new Bean(i + "_1", i + "_2", i + "_3", i + "_4", i + "_5", i + "_6", i + "_7", i + "_8", i + "_9", i + "_10");
            model.addBean(bean);
        }



        AdvanceViewer.supportSort(tableViewer,model,"beanList",new String[]{"0","1","2","3","4","5","6","7","8","9"},new String[]{"s0","s1","s2","s3","s4","s5","s6","s7","s8","s9"});
    }

    public void bind(){
        Realm realm = SWTObservables.getRealm(Display.getCurrent());

        ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
        IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(), Bean.class, new String[]{"s0","s1","s2","s3","s4","s5","s6","s7","s8","s9"});
        tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
        tableViewer.setContentProvider(listContentProvider);

        IObservableList iObservableList = BeansObservables.observeList(realm, model, "beanList");
        tableViewer.setInput(iObservableList);
    }
}
