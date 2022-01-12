/**
 * Application.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.finkok.facturacion.stamp;

public interface Application extends java.rmi.Remote {

    /**
     * This function provides the stamping service to resellers
     */
    public views.core.soap.services.apps.AcuseRecepcionCFDI stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * This function provides the sign stamping service to resellers
     */
    public views.core.soap.services.apps.AcuseRecepcionCFDI sign_stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * This function returns the stamp information of an invoice
     */
    public views.core.soap.services.apps.AcuseRecepcionCFDI stamped(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * This function provides the quick stamping service to resellers
     */
    public views.core.soap.services.apps.AcuseRecepcionCFDI quick_stamp(byte[] xml, java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * This method returns the status of a peding buffer invoice by
     * using an uuid
     */
    public views.core.soap.services.apps.QueryPendingResult query_pending(java.lang.String username, java.lang.String password, java.lang.String uuid) throws java.rmi.RemoteException;
}
