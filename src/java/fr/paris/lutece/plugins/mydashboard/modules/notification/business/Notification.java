/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.mydashboard.modules.notification.business;

import java.sql.Date;

/**
 * 
 * Notification
 *
 */
public class Notification
{
    private int _nIdNotification;
    private boolean _bRead;
    private String _strIdUser;
    private String _strObject;
    private String _strMessage;
    private Date _dateCreation;
    private String _strSender;

    /**
     * @return the _nIdNotification
     */
    public int getIdNotification( )
    {
        return _nIdNotification;
    }

    /**
     * @param nIdNotification
     *            the _nIdNotification to set
     */
    public void setIdNotification( int nIdNotification )
    {
        this._nIdNotification = nIdNotification;
    }

    /**
     * @return the _bRead
     */
    public boolean isRead( )
    {
        return _bRead;
    }

    /**
     * @param bRead
     *            the _bRead to set
     */
    public void setRead( boolean bRead )
    {
        this._bRead = bRead;
    }

    /**
     * @return the _nIdUser
     */
    public String getIdUser( )
    {
        return _strIdUser;
    }

    /**
     * @param strIdUser
     *            the _strIdUser to set
     */
    public void setIdUser( String strIdUser )
    {
        this._strIdUser = strIdUser;
    }

    /**
     * @return the _strObject
     */
    public String getObject( )
    {
        return _strObject;
    }

    /**
     * @param strObject
     *            the _strObject to set
     */
    public void setObject( String strObject )
    {
        this._strObject = strObject;
    }

    /**
     * @return the _strMessage
     */
    public String getMessage( )
    {
        return _strMessage;
    }

    /**
     * @param strMessage
     *            the _strMessage to set
     */
    public void setMessage( String strMessage )
    {
        this._strMessage = strMessage;
    }

    /**
     * @return the _dateCreation
     */
    public Date getDateCreation( )
    {
        return _dateCreation;
    }

    /**
     * @param dateCreation
     *            the _dateCreation to set
     */
    public void setDateCreation( Date dateCreation )
    {
        this._dateCreation = dateCreation;
    }

    /**
     * @return the _strSender
     */
    public String getSender( )
    {
        return _strSender;
    }

    /**
     * @param strSender
     *            the _strSender to set
     */
    public void setSender( String strSender )
    {
        this._strSender = strSender;
    }
}
