import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Administrator on 2016/5/18.
 */
public class AbstractBean {
    protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
