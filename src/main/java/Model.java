import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Model extends  AbstractBean{
    private List<Bean> beanList = new ArrayList<Bean>();

    public List<Bean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Bean> beanList) {
        this.beanList.clear();
        this.beanList.addAll(beanList);
        pcs.firePropertyChange("beanList",null,null);
    }

    public void addBean(Bean bean){
        this.beanList.add(bean);
        pcs.firePropertyChange("beanList",null,null);
    }
}
