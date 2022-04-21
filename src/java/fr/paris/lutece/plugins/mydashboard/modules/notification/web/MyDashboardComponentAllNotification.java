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
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.mydashboard.modules.notification.business.Notification;
import fr.paris.lutece.plugins.mydashboard.modules.notification.service.INotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.service.NotificationService;
import fr.paris.lutece.plugins.mydashboard.modules.notification.util.NotificationConstants;
import fr.paris.lutece.plugins.mydashboard.service.MyDashboardComponent;
import fr.paris.lutece.portal.service.i18n.I18nService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.SecurityService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.AbstractPaginator;
import fr.paris.lutece.util.html.HtmlTemplate;

/**
 * 
 * MyDashboardComponentAllNotification
 *
 */
public class MyDashboardComponentAllNotification extends MyDashboardComponent
{

    /**
     * 
     */
    private static final long serialVersionUID = 8297192924908575568L;

    private static final String TEMPLATE_ALL_NOTIFICATION_LIST = "skin/plugins/mydashboard/modules/notification/dashboard_all_notification.html";
    private static final String DASHBOARD_COMPONENT_ID = "notification.myDashboardComponentAllNotification";
    private static final String MESSAGE_COMPONENT_DESCRIPTION = "module.mydashboard.notification.myDashboardComponentAllNotification.description";

    @Inject
    @Named( NotificationService.BEAN_NAME )
    private INotificationService _notificationService;

    @Override
    public String getDashboardData( HttpServletRequest request )
    {
        LuteceUser user = SecurityService.getInstance( ).getRegisteredUser( request );
        String strPanel = request.getParameter( NotificationConstants.PARAMETER_PANEL );
        boolean isPanelAll = StringUtils.isNotEmpty( strPanel ) && strPanel.equals( NotificationConstants.MES_NOTIFICATIONS );

        if ( user != null && isPanelAll )
        {
            Map<String, Object> model = new HashMap<>( );

            HttpSession session = request.getSession( true );

            String strCurrentPageIndex = session.getAttribute( NotificationConstants.CURRENT_PAGE_INDEX ) != null
                    ? (String) session.getAttribute( NotificationConstants.CURRENT_PAGE_INDEX )
                    : null;
            
            strCurrentPageIndex = AbstractPaginator.getPageIndex( request, AbstractPaginator.PARAMETER_PAGE_INDEX, strCurrentPageIndex );
            session.setAttribute( NotificationConstants.CURRENT_PAGE_INDEX, strCurrentPageIndex );

            int nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( NotificationConstants.PROPERTY_NUMBER_OF_DEMAND_PER_PAGE, 10 );

            List<Notification> listNotification = _notificationService.findNotificationsByUser( user.getName( ), null );

            // PAGINATOR
            LocalizedPaginator<Notification> paginator = new LocalizedPaginator<>( listNotification, nDefaultItemsPerPage,
                    AppPropertiesService.getProperty( NotificationConstants.PROPERTY_URL_MES_NOTIFICATIONS ), AbstractPaginator.PARAMETER_PAGE_INDEX,
                    strCurrentPageIndex, request.getLocale( ) );

            model.put( NotificationConstants.MARK_NB_ITEMS_PER_PAGE, "" + nDefaultItemsPerPage );
            model.put( NotificationConstants.MARK_PAGINATOR, paginator );
            model.put( NotificationConstants.MARK_NOTIFICATION_LIST, paginator.getPageItems( ) );

            HtmlTemplate htmTemplate = AppTemplateService.getTemplate( TEMPLATE_ALL_NOTIFICATION_LIST, request.getLocale( ), model );

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
