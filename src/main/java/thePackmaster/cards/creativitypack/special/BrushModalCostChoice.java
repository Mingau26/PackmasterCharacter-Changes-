package thePackmaster.cards.creativitypack.special;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.creativitypack.AbstractCreativityCard;
import thePackmaster.util.JediUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class BrushModalCostChoice extends AbstractCreativityCard {
    public final static String ID = makeID(BrushModalCostChoice.class.getSimpleName());
    public final static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup");
    public final static String[] EXTENDED_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;
    public BrushModalCostChoice(int cost, CardType type) {
        super(ID, cost, type, CardRarity.SPECIAL, CardTarget.NONE);
        switch (type) {
            case ATTACK:
                name = uiStrings.TEXT[0];
                break;
            case SKILL:
                name = uiStrings.TEXT[1];
                break;
            case POWER:
                name = uiStrings.TEXT[2];
                break;
            default:
                name = uiStrings.TEXT[5];
                break;
        }
        initializeTitle();
        if (cost == 3) rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void onChoseThisOption()
    {
        addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(JediUtil.filterCardsForDiscovery(
                c -> c.type == type &&
                        ((cost == 3 && c.cost >= cost) || c.cost == cost) &&
                        !c.hasTag(CardTags.HEALING) &&
                        c.rarity != CardRarity.SPECIAL)
        ),
                crd -> {},
                false));
    }
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void upp() {
    }
}
