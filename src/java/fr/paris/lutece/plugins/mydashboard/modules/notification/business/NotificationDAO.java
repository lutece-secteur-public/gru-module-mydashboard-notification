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

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.paris.lutece.util.sql.DAOUtil;

/**
 * 
 * NotificationDAO
 *
 */
public class NotificationDAO implements INotificationDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT = "SELECT id_notification, is_read, id_user, object, message, date_creation, sender FROM mydashboard_notification WHERE id_notification = ? ORDER BY date_creation desc, is_read";
    private static final String SQL_QUERY_INSERT = "INSERT INTO mydashboard_notification ( id_notification, is_read, id_user, object, message, date_creation, sender ) VALUES ( ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE = "DELETE FROM mydashboard_notification WHERE id_notification = ? ";
    private static final String SQL_QUERY_UPDATE_READ = "UPDATE mydashboard_notification SET is_read = ? WHERE id_notification = ?";
    private static final String SQL_QUERY_SELECT_USER = "SELECT id_notification, is_read, id_user, object, message, date_creation, sender FROM mydashboard_notification WHERE id_user = ? ORDER BY date_creation desc, is_read";

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert( Notification notification )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, Statement.RETURN_GENERATED_KEYS ) )
        {
            int nIndex = 0;
            daoUtil.setInt( ++nIndex, notification.getIdNotification( ) );
            daoUtil.setBoolean( ++nIndex, notification.isRead( ) );
            daoUtil.setString( ++nIndex, notification.getIdUser( ) );
            daoUtil.setString( ++nIndex, notification.getObject( ) );
            daoUtil.setString( ++nIndex, notification.getMessage( ) );
            daoUtil.setDate( ++nIndex, notification.getDateCreation( ) );
            daoUtil.setString( ++nIndex, notification.getSender( ) );

            daoUtil.executeUpdate( );

            if ( daoUtil.nextGeneratedKey( ) )
            {
                notification.setIdNotification( daoUtil.getGeneratedKeyInt( 1 ) );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertListNotification( List<Notification> listNotifications )
    {

        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT ) )
        {
            for ( Notification notification : listNotifications )
            {
                int nIndex = 0;
                daoUtil.setInt( ++nIndex, notification.getIdNotification( ) );
                daoUtil.setBoolean( ++nIndex, notification.isRead( ) );
                daoUtil.setString( ++nIndex, notification.getIdUser( ) );
                daoUtil.setString( ++nIndex, notification.getObject( ) );
                daoUtil.setString( ++nIndex, notification.getMessage( ) );
                daoUtil.setDate( ++nIndex, notification.getDateCreation( ) );
                daoUtil.setString( ++nIndex, notification.getSender( ) );

                daoUtil.addBatch( );

                daoUtil.clearParameters( );
            }

            daoUtil.executeBatch( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateReadStatus( int nIdNotification, boolean isRead )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE_READ ) )
        {
            int nIndex = 0;
            daoUtil.setBoolean( ++nIndex, isRead );
            daoUtil.setInt( ++nIndex, nIdNotification );

            daoUtil.executeUpdate( );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Notification> load( int nIdNotification )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT ) )
        {

            daoUtil.setInt( 1, nIdNotification );
            daoUtil.executeQuery( );

            Notification notification = null;

            while ( daoUtil.next( ) )
            {
                int nIndex = 0;
                notification = new Notification( );

                notification.setIdNotification( daoUtil.getInt( ++nIndex ) );
                notification.setRead( daoUtil.getBoolean( ++nIndex ) );
                notification.setIdUser( daoUtil.getString( ++nIndex ) );
                notification.setObject( daoUtil.getString( ++nIndex ) );
                notification.setMessage( daoUtil.getString( ++nIndex ) );
                notification.setDateCreation( daoUtil.getDate( ++nIndex ) );
                notification.setSender( daoUtil.getString( ++nIndex ) );
            }

            return Optional.ofNullable( notification );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Notification> findNotificationsByUser( String strIdUser, Integer nLimit )
    {
        String strRequest = SQL_QUERY_SELECT_USER;
        if ( nLimit != null )
        {
            strRequest += " LIMIT " + nLimit;
        }
        try ( DAOUtil daoUtil = new DAOUtil( strRequest ) )
        {
            daoUtil.setString( 1, strIdUser );
            daoUtil.executeQuery( );

            List<Notification> listNotification = new ArrayList<>( );

            while ( daoUtil.next( ) )
            {
                int nIndex = 0;
                Notification notification = new Notification( );

                notification.setIdNotification( daoUtil.getInt( ++nIndex ) );
                notification.setRead( daoUtil.getBoolean( ++nIndex ) );
                notification.setIdUser( daoUtil.getString( ++nIndex ) );
                notification.setObject( daoUtil.getString( ++nIndex ) );
                notification.setMessage( daoUtil.getString( ++nIndex ) );
                notification.setDateCreation( daoUtil.getDate( ++nIndex ) );
                notification.setSender( daoUtil.getString( ++nIndex ) );

                listNotification.add( notification );
            }

            return listNotification;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete( int nIdNotification )
    {
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE ) )
        {
            daoUtil.setInt( 1, nIdNotification );
            daoUtil.executeUpdate( );

        }
    }

}
