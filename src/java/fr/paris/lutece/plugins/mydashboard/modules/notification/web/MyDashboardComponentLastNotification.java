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
package fr.paris.lutece.plugins.mydashboard.modules.notification.web;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.mydashboard.modules.notification.service.INotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.service.NotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.util.NotificationConstants;
import fr.paris.lutece.plugins.mydashboard.service.MyDashboardComponent;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.html.HtmlTemplate;

/**
 * 
 * MyDashboardComponentLastNotification
 *
 */
public class MyDashboardComponentLastNotification extends MyDashboardComponent
{

    /**
     * 
     */
    private static final long serialVersionUID = 8297192924908575568L;

    private static final String TEMPLATE_LAST_NOTIFICATION_LIST = "skin/plugins/mydashboard/modules/notification/dashboard_last_notification.html";
    private static final String DASHBOARD_COMPONENT_ID = "notification.myDashboardComponentLastNotification";
    private static final String MESSAGE_COMPONENT_DESCRIPTION = "module.mydashboard.notification.myDashboardComponentLastNotification.description";

    @Inject
    @Named( NotificationService.BEAN_NAME )
    private INotificationService _notificationService;

    @Override
    public String getDashboardData( HttpServletRequest request )
    {
        LuteceUser user = SecurityService.getInstance( ).getRegisteredUser( request );

        String strPanel = request.getParameter( NotificationConstants.PARAMETER_PANEL );
        boolean isPanelAll = !( StringUtils.isNotEmpty( strPanel ) && strPanel.equals( NotificationConstants.MES_NOTIFICATIONS ) );

        if ( user != null && isPanelAll )
        {
            Map<String, Object> model = new HashMap<>( );

            model.put( NotificationConstants.MARK_NOTIFICATION_LIST, _notificationService.findNotificationsByUser( user.getName( ),
                    AppPropertiesService.getPropertyInt( NotificationConstants.PROPERTY_NUMBER_OF_NOTIFICATION, 5 ) ) );

            HtmlTemplate htmTemplate = AppTemplateService.getTemplate( TEMPLATE_LAST_NOTIFICATION_LIST, request.getLocale( ), model );

            return htmTemplate.getHtml( );
        }

        return StringUtils.EMPTY;
    }

    @Override
    public String getComponentId( )
    {
        return DASHBOARD_COMPONENT_ID;
    }

    @Override
    public String getComponentDescription( Locale locale )
    {
        return I18nService.getLocalizedString( MESSAGE_COMPONENT_DESCRIPTION, locale );
    }

}
