package SwordMaster.utils;

public class StatusManage {
    boolean upgraded = false;

    public int getNewValue(boolean hasPower) {
        if (hasPower) {
            if (!upgraded) {
                upgraded = true;
                return 1;
            }
        } else {
            if (upgraded) {
                upgraded = false;
                return -1;
            }
        }
        return 0;
    }
}
