/**
 * Application.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.finkok.facturacion.cancel;

public interface Application extends java.rmi.Remote {

    /**
     * This method cancels a CFDI by the given enveloped signature
     */
    public views.core.soap.services.apps.CancelaCFDResult cancel_signature(byte[] xml, java.lang.String username, java.lang.String password, java.lang.Boolean store_pending) throws java.rmi.RemoteException;

    /**
     * This method returns the status of a peding buffer invoice by
     * using an uuid
     */
    public views.core.soap.services.apps.QueryPendingResult query_pending_cancellation(java.lang.String username, java.lang.String password, java.lang.String uuid) throws java.rmi.RemoteException;

    /**
     * This method returns a session id for work with the webservices
     */
    public views.core.soap.services.apps.CancelaCFDResult sign_cancel(views.core.soap.services.apps.UUIDS UUIDS, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, java.lang.String serial, java.lang.Boolean store_pending) throws java.rmi.RemoteException;

    /**
     * This method returns the sat receipt (response) of the cancellation
     * webservice
     */
    public views.core.soap.services.apps.ReceiptResult get_receipt(java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, java.lang.String uuid, java.lang.String type) throws java.rmi.RemoteException;

    /**
     * This method returns a session id for work with the webservices
     */
    public views.core.soap.services.apps.CancelaCFDResult cancel(views.core.soap.services.apps.UUIDS UUIDS, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, byte[] cer, byte[] key, java.lang.Boolean store_pending) throws java.rmi.RemoteException;
    public views.core.soap.services.apps.CancelaCFDResult out_cancel(byte[] xml, java.lang.String username, java.lang.String password, java.lang.String taxpayer_id, byte[] cer, byte[] key, java.lang.Boolean store_pending) throws java.rmi.RemoteException;
}
