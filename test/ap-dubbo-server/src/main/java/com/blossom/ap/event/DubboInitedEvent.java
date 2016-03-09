//*************************************************************************
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*                                                                       *
//*                COPYRIGHT (C) FINANCING CITY CORPORATION 2012          *
//*    ALL RIGHTS RESERVED BY FINANCING CITY CORPORATION. THIS PROGRAM    *
//* MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY    *
//* FINANCING CITY CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED *
//* OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN         *
//* PERMISSION OF FINANCING CITY CORPORATION. USE OF COPYRIGHT NOTICE     *
//* DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                         *
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*************************************************************************

package com.blossom.ap.event;


import org.springframework.context.ApplicationEvent;

/**
 * DubboInitedEvent
 *
 * @author zhangshsh
 *         作成 2014/04/04
 */
public class DubboInitedEvent extends ApplicationEvent {

    public DubboInitedEvent(Object source){
        super(source);
    }

}