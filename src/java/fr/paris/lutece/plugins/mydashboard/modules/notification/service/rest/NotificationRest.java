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
package fr.paris.lutece.plugins.mydashboard.modules.notification.service.rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import fr.paris.lutece.plugins.mydashboard.modules.notification.business.Notification;
import fr.paris.lutece.plugins.mydashboard.modules.notification.service.INotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.service.NotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.util.NotificationConstants;
import fr.paris.lutece.plugins.rest.service.RestConstants;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * 
 * NotificationRest
 *
 */
@Path( RestConstants.BASE_PATH + "notification-api" )
public class NotificationRest
{
    @Inject
    @Named( NotificationService.BEAN_NAME )
    private INotificationService _notificationService;

    private ObjectMapper _objectMapper = new ObjectMapper( );

    @POST
    @Path( "/add" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addNotification( String jsonNotification )
    {
        if ( StringUtils.isNotEmpty( jsonNotification ) )
        {
            Notification notification;
            try
            {
                notification = _objectMapper.readValue( jsonNotification, Notification.class );
                _notificationService.create( notification );

                return Response.ok( NotificationConstants.JSON_SUCCESS_RESPONSE ).build( );

            }
            catch( IOException e )
            {
                AppLogService.error( "Notification-api: Une erreur s'est produite lors de la création une notification", e );
                return Response.serverError( ).build( );
            }

        }
        return Response.noContent( ).build( );
    }

    @POST
    @Path( "/addList" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public Response addListNotification( String jsonNotification )
    {
        if ( StringUtils.isNotEmpty( jsonNotification ) )
        {
            List<Notification> listNotification;
            try
            {
                listNotification = _objectMapper.readValue( jsonNotification, new TypeReference<List<Notification>>( )
                {
                } );

                _notificationService.createListNotification( listNotification );

                return Response.ok( NotificationConstants.JSON_SUCCESS_RESPONSE ).build( );
            }
            catch( IOException e )
            {
                AppLogService.error( "Notification-api: Une erreur s'est produite lors de la création une liste de notification", e );
                return Response.serverError( ).build( );
            }

        }
        return Response.noContent( ).build( );
    }

    @GET
    @Path( "/{id}" )
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getNotification( @PathParam( "id" ) int nIdNotification )
    {
        Optional<Notification> notification = _notificationService.findByPrimaryKey( nIdNotification );

        if ( notification.isPresent( ) )
        {
            try
            {
                String strResult = _objectMapper.writeValueAsString( notification.get( ) );
                return Response.ok( strResult ).build( );
            }
            catch( IOException e )
            {
                AppLogService.error( "Notification-api: Une erreur s'est produite lors de la récupération une notification", e );
                return Response.serverError( ).build( );
            }
        }
        return Response.noContent( ).build( );
    }

    @GET
    @Path( "/list/{userId}" )
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getNotificationsByGuid( @PathParam( "userId" ) String strUserId )
    {
        if ( StringUtils.isNotEmpty( strUserId ) )
        {
            List<Notification> listNotification = _notificationService.findNotificationsByUser( strUserId, null );

            if ( !listNotification.isEmpty( ) )
            {
                try
                {
                    String strResult = _objectMapper.writeValueAsString( listNotification );
                    return Response.ok( strResult ).build( );
                }
                catch( IOException e )
                {
                    AppLogService.error( "Notification-api: Une erreur s'est produite lors de la récupération des notifications par guid", e );
                    return Response.serverError( ).build( );
                }
            }
        }
        return Response.noContent( ).build( );
    }

    @DELETE
    @Path( "/{id}" )
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Response deleteNotification( @PathParam( "id" ) int nIdNotification )
    {
        Optional<Notification> notification = _notificationService.findByPrimaryKey( nIdNotification );
        if ( notification.isPresent( ) )
        {
            _notificationService.remove( nIdNotification );

            return Response.ok( NotificationConstants.JSON_SUCCESS_RESPONSE ).build( );
        }
        else
        {
            return Response.noContent( ).build( );
        }
    }
}
