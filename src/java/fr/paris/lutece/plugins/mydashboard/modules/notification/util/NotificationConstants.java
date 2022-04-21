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
package fr.paris.lutece.plugins.mydashboard.modules.notification.util;

/**
 * 
 * NotificationUtils
 *
 */
public final class NotificationConstants
{
    /**
     * Private constructor
     */
    private NotificationConstants( )
    {

    }

    public static final String MARK_NOTIFICATION_LIST 				= "notification_list";
    public static final String MARK_NB_ITEMS_PER_PAGE 				= "nb_items_per_page";
    public static final String MARK_PAGINATOR 						= "paginator";

    public static final String PROPERTY_URL_MES_NOTIFICATIONS 		= "mydashboard-notification.url.mesdemarches";
    public static final String PROPERTY_NUMBER_OF_DEMAND_PER_PAGE 	= "mydashboard-notification.numberOfNotificationPerPage";
    public static final String PROPERTY_NUMBER_OF_NOTIFICATION 		= "mydashboard-notification.numberOfNotificationDisplay";

    public static final String PARAMETER_PANEL 						= "panel";
    public static final String PARAMETR_ID 							= "idNotification";

    public static final String MES_NOTIFICATIONS 					= "mes_notifications";

    public static final String CURRENT_PAGE_INDEX 					= "current_page_index";

    public static final String JSON_STATUS 							= "status";
    
    public static final String JSON_SUCCESS_RESPONSE 				= "{\"status\": \"success\"}";
    
}
