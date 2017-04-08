package opendota.combatlogvisitors;

import static opendota.combatlogvisitors.CombatLogConstants.DOTA_COMBATLOG_DEATH;
import static opendota.combatlogvisitors.CombatLogConstants.DOTA_COMBATLOG_MODIFIER_ADD;
import static opendota.combatlogvisitors.CombatLogConstants.DOTA_COMBATLOG_MODIFIER_REMOVE;

import java.util.HashMap;

import opendota.Parse.Entry;

public class BountyHunterTrackVisitor implements Visitor<Boolean> {

	private static final String BH_TRACK = "modifier_bounty_hunter_track";

	HashMap<String, Boolean> trackStatus = new HashMap<String, Boolean>();

	@Override
	public Boolean visit(Entry entry) {
		if (entry.type.equals(DOTA_COMBATLOG_MODIFIER_ADD) && entry.inflictor.equals(BH_TRACK)) {
			trackStatus.put(entry.targetname, true);
		}

		if (entry.type.equals(DOTA_COMBATLOG_MODIFIER_REMOVE) && entry.inflictor.equals(BH_TRACK)) {
			trackStatus.put(entry.targetname, false);
		}

		if (entry.type.equals(DOTA_COMBATLOG_DEATH) && trackStatus.getOrDefault(entry.targetname, false)) {
			return true;
		}

		return null;
	}
}
