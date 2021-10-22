//package com.iluwatar.pessimisticofflinelock;
//
//import org.bukkit.plugin.java.JavaPlugin;
//import org.eclipse.persistence.exceptions;
//
//public interface MutexLockManager {
//  public static final MutexLockManager INSTANCE =
//      (MutexLockManager) Plugins.getPlugin(MutexLockManager.class);
//
//  public void lock(Long customerID, String owner) throws ConcurrencyException;
//
//  public void unlock(Long customerID, String owner);
//
//  public void unlockAll(String owner);
//}