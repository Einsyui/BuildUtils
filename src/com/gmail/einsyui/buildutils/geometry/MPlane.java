package com.gmail.einsyui.buildutils.geometry;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class MPlane {

	Location origin;
	Vector normal;
	
	public MPlane(Location origin, Vector normal){
		this.origin=origin; this.normal=normal.normalize();
	}
	public MPlane(Location origin, Vector u, Vector v){
		this.origin = origin;
		this.normal = u.crossProduct(v).normalize();
	}
	public MPlane(Location origin, Location p, Location q){
		this(origin, p.subtract(origin).toVector(), q.subtract(origin).toVector());
	}
	
	public Vector orthogonalProjection(Vector v){
		return Utils.getComponentOrthogonalTo(v, normal.clone());
	}
	public Location getNearest(Location l){
		return Utils.getComponentOrthogonalTo((l.subtract(origin).toVector()), normal.clone())
				.toLocation(l.getWorld());
	}
	
	public double getSignedDistance(Location l){
		return (l.subtract(origin).toVector()).dot(normal.normalize());
	}
	public double distance(Location l){
		return Math.abs(getSignedDistance(l));
	}
	public Vector normal() {
		return normal;
	}
}
