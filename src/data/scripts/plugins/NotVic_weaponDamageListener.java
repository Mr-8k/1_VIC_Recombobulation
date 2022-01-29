package data.scripts.plugins;

import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamageAPI;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.listeners.DamageDealtModifier;
import org.lwjgl.util.vector.Vector2f;


public class NotVic_weaponDamageListener implements DamageDealtModifier {

    @Override
    public String modifyDamageDealt(Object param, CombatEntityAPI target, DamageAPI damage, Vector2f point, boolean shieldHit) {

        if (param instanceof DamagingProjectileAPI) {
            if (((DamagingProjectileAPI) param).getProjectileSpecId() != null) {
                String projID = ((DamagingProjectileAPI) param).getProjectileSpecId();
                switch (projID) {
                    case "vic_heavylaidlawaccelerator_shot":
                    case "vic_laidlawMassDriver_shot":
                        if (shieldHit) {
                            damage.setSoftFlux(false);
                            //damage.setForceHardFlux(true);
                        }
                        break;
                }
            }
        }
        return null;
    }
}
