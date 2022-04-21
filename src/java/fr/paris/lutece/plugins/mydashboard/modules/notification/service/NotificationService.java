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
package fr.paris.lutece.plugins.mydashboard.modules.notification.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import fr.paris.lutece.plugins.mydashboard.modules.notification.business.INotificationDAO;
import fr.paris.lutece.plugins.mydashboard.modules.notification.business.Notification;

/**
 * 
 * NotificationService
 *
 */
public class NotificationService implements INotificationService
{

    public static final String BEAN_NAME = "mydashboard-notification.notificationService";

    @Inject
    INotificationDAO _notificationDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void create( Notification notification )
    {
        _notificationDAO.insert( notification );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createListNotification( List<Notification> listNotifications )
    {
        _notificationDAO.insertListNotification( listNotifications );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateReadStatus( int nIdNotification, boolean isRead )
    {
        _notificationDAO.updateReadStatus( nIdNotification, isRead );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Notification> findByPrimaryKey( int nIdNotification )
    {
        return _notificationDAO.load( nIdNotification );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Notification> findNotificationsByUser( String nIdUser, Integer nLimit )
    {
        return _notificationDAO.findNotificationsByUser( nIdUser, nLimit );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove( int nIdNotification )
    {
        _notificationDAO.delete( nIdNotification );
    }
}
