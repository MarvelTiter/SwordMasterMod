package SwordMaster.cards;

import basemod.abstracts.CustomCard;
import basemod.interfaces.OnPlayerTurnStartSubscriber;

import java.util.function.Function;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import SwordMaster.powers.FlowingStance;
import SwordMaster.powers.FlowingStanceForcePower;
import SwordMaster.relics.AttackCounter;
import SwordMaster.characters.swordMaster;

public abstract class Master_AbstractCard extends CustomCard implements OnPlayerTurnStartSubscriber {
    public Master_AbstractCard(String id, String name, String img, int cost, String rawDescription, CardType type,
            CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    /**
     * 获得一层流心buff
     * 
     * @param p
     */
    public void ApplyFlowingStance(AbstractPlayer p) {
        addToBot(new ApplyPowerAction(p, p, new FlowingStance(p, 1), 1));
    }

    /**
     * 检查是否有流心buff
     * 
     * @return
     */
    public boolean hasStance() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null)
            return false;
        return player.hasPower(FlowingStance.POWER_ID);
    }

    /**
     * 检查是否有流心狂buff
     * 
     * @return
     */
    public boolean hasStanceForce() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null)
            return false;
        return player.hasPower(FlowingStanceForcePower.POWER_ID);
    }

    /**
     * 使用后失去一层流心buff
     */
    public void ReduceFlowingStance() {
        if (!hasStance())
            return;
        AbstractPlayer player = AbstractDungeon.player;
        addToTop(new ReducePowerAction(player, player, FlowingStance.POWER_ID, 1));
    }

    /**
     * 获取本场战斗攻击次数
     * 
     * @return
     */
    public int getAttackCount() {
        AbstractPlayer player = AbstractDungeon.player;
        if (player == null) {
            return 0;
        }
        AbstractRelic counter = player.getRelic(AttackCounter.ID);
        return counter.counter;
    }

    boolean upgradedStanceDamage = false;

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (hasTag(swordMaster.Enums.FlowingStance) && hasStance()) {
            {
                int n = preApplyStanceForDamage();
                if (!upgradedStanceDamage) {
                    upgradeDamage(n);
                } else {
                    upgradeDamage(0);
                    upgradedStanceDamage = false;
                }
            }
            {
                int n = preApplyStanceForMagicNumber();
            }
            applyStanceForCost();
            applyStanceForBlock();
        }
        if (hasTag(swordMaster.Enums.FlowingForce) && hasStanceForce()) {
            applyStanceForceForDamage();
            applyStanceForceForMagicNumber();
            applyStanceForceForCost();
            applyStanceForceForBlock();
        }
    }

    public int preApplyStanceForDamage() {
        return 0;
    }

    public int preApplyStanceForMagicNumber() {
        return 0;
    }

    public void applyStanceForCost() {
    }

    public void applyStanceForBlock() {
    }

    public void applyStanceForceForDamage() {
    }

    public void applyStanceForceForMagicNumber() {
    }

    public void applyStanceForceForCost() {
    }

    public void applyStanceForceForBlock() {
    }

    @Override
    public void receiveOnPlayerTurnStart() {

    }
}
