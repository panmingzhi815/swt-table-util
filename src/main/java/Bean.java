/**
 * Created by Administrator on 2016/5/18.
 */
public class Bean extends AbstractBean{

    private String s0;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;

    public Bean() {
    }

    public Bean(String s0, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9) {
        this.s0 = s0;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
    }

    public String getS0() {
        return s0;
    }

    public void setS0(String s0) {
        pcs.firePropertyChange("s0",this.s0,this.s0 = s0);
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        pcs.firePropertyChange("s1",this.s1,this.s1 = s1);
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        pcs.firePropertyChange("s2",this.s2,this.s2 = s2);
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        pcs.firePropertyChange("s3",this.s3,this.s3 = s3);
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        pcs.firePropertyChange("s4",this.s4,this.s4 = s4);
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        pcs.firePropertyChange("s5",this.s5,this.s5 = s5);
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        pcs.firePropertyChange("s6",this.s6,this.s6 = s6);
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        pcs.firePropertyChange("s7",this.s7,this.s7 = s7);
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        pcs.firePropertyChange("s8",this.s8,this.s8 = s8);
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        pcs.firePropertyChange("s9",this.s9,this.s9 = s9);
    }
}
