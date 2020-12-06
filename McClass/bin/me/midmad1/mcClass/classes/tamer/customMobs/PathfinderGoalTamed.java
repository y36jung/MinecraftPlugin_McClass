package me.midmad1.mcClass.classes.tamer.customMobs;

import java.util.EnumSet;

import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.PathfinderGoal;
import net.minecraft.server.v1_16_R2.RandomPositionGenerator;
import net.minecraft.server.v1_16_R2.Vec3D;

public class PathfinderGoalTamed extends PathfinderGoal {

	private EntityInsentient tamed = null; // Tamed Mob
	private EntityLiving player; // Player
	
	private double speed = 0;
	private float dist = 0;
	
	private double x;
	private double y;
	private double z;
	
	public PathfinderGoalTamed(EntityInsentient tamed, double speed, float dist) {
		this.tamed = tamed;
		this.speed = speed;
		this.dist = dist;
		this.a(EnumSet.of(Type.MOVE, Type.TARGET));
	}
	
	@Override
	public boolean a() {
		// starts the Pathfinding goal if it is true
		// runs every tick
		this.player = this.tamed.getGoalTarget();
		if (this.player == null) {
			return false;
		} else if (this.tamed.getDisplayName() == null) {
			return false;
		} else if (!(this.tamed.getDisplayName().toString().contains(this.player.getName()))) {
			return false;
		} else if (this.player.h(this.tamed) > (double) (this.dist * this.dist)) {
			tamed.setPosition(this.player.locX(), this.player.locY(), this.player.locZ());
			return false;
		} else {
			Vec3D vec = RandomPositionGenerator.a((EntityCreature) this.tamed,
					16, 7, this.player.getPositionVector());
			
			if (vec == null) {
				return false;
			}
			
			this.x = this.player.locX();
			this.y = this.player.locY();
			this.z = this.player.locZ();
			return true;
		}
	}
	
	// runner
	public void c() {
		this.tamed.getNavigation().a(this.x, this.y, this.z, this.speed);
	}
	
	// runs after c()
	// run every tick as long as its true (then repeats c)
	public boolean b() {
		return !this.tamed.getNavigation().m() && this.player.h(this.tamed) <
				(double) (this.dist * this.dist);
	}
	
	// runs when b() returns false
	public void d() {
		this.player = null;
	}

}
