package SwordMaster.listener;

import java.util.EventListener;

public interface FlowingStanceChange extends EventListener {
    public void receiveFlowingStanceStackChange(boolean change, int amount);
}
