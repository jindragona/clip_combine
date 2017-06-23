/*
 *  MIDAS version 1.0
 *
 *  Copyright ⓒ 2017 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */
package com.kt.midas.adm.common.util;

import org.apache.commons.lang.math.NumberUtils;
//import org.apache.commons.configuration.Configuration;
//import coperframe.common.config.ConfigHelper;


public class Pager {

    private static final String PAGING_PARAM_NAME = "pg";

    private static final String FIRST_MOVE_TAG = "처음";

    private static final String LAST_MOVE_TAG = "마지막";

    private static final String PREV_MOVE_TAG = "이전";

    private static final String NEXT_MOVE_TAG = "다음";

    private static final String PAGE_NUM_START_TAG = "[";

    private static final String PAGE_NUM_END_TAG = "]";

    private String callFun;

    private int iPerPgLin = 10;

    private int iPgGrpCnt = 10;

    private int iCurPg;

    private int iAllRowCnt;

    private String frstMovTag;

    private String lastMovTag;

    private String prevMovTag;

    private String nextMovTag;

    private String pageNumStartTag;

    private String pageNumEndTag;

    private String lineHeaderTag;

    private String lineTailTag;

    private String pagingParamName;
    
    private String adjustLineFun = null;

    //private Configuration conf = null;

	private boolean isAdjustAll = true;
	
	private int iMaxLine = 100;

	private String minId = null;

	private String maxId = null;

    public Pager() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     *
     * @param callJsFun : 페이지번호 선택시 호출된 자바스크립트 함수명명, javastript 인터페이스 : 함수명(페이지번호,페이지당라인수)
     * @param perPgLin : 한페이지에 보여질 라인수.
     * @param pgGrpCnt : 페이징출력 번호의 갯수
     */
    public Pager(String callJsFun, String perPgLin, String pgGrpCnt) {
        this(callJsFun, NumberUtils.toInt(perPgLin), NumberUtils.toInt(pgGrpCnt));
    }

    public Pager(String callJsFun) {
        
        this.callFun = callJsFun;
        /*
        if(conf == null){
        	conf = ConfigHelper.getConfiguration();
        }
        this.iPerPgLin = conf.getInt("PAGING.BACK.PERLINE");
        this.iPgGrpCnt = conf.getInt("PAGING.BACK.NUMCNT");
        */
    }

    public Pager(String callJsFun, int iPerPgLin) {

    	this.callFun = callJsFun;
        this.iPerPgLin = iPerPgLin;
        /*
        if(conf == null){
        	conf = ConfigHelper.getConfiguration();
        }
        this.iPgGrpCnt = conf.getInt("PAGING.BACK.NUMCNT");
        */
    }

    public Pager(String callJsFun, int iPerPgLin, int iPgGrpCnt) {
        this.callFun = callJsFun;
        this.iPerPgLin = iPerPgLin;
        this.iPgGrpCnt = iPgGrpCnt;
    }

    
    // 공통게시판 관련 Pager 생성자
    public Pager(String callJsFun, String perPgLin, String pgGrpCnt, String maxId, String minId) {
    	this(callJsFun, NumberUtils.toInt(perPgLin), NumberUtils.toInt(pgGrpCnt), maxId, minId);
    }
    
    public Pager(String callJsFun, int iPerPgLin, int iPgGrpCnt, String maxId, String minId) {
        this.callFun = callJsFun;
        this.iPerPgLin = iPerPgLin;
        this.iPgGrpCnt = iPgGrpCnt;
        this.maxId = maxId;
        this.minId = minId;
    }

	/**
     * request 파라메터에서 접근값을 직접넘길때 편리하게 사용하는 문자형
     * @param curPg
     */
    public void setCurrentPage(String curPg) {
        setCurrentPage(NumberUtils.toInt(curPg, 1));
    }
    
    /**
     * 현재 페이지 번호 설정
     * @param iCurPg
     */
    public void setCurrentPage(int iCurPg) {
        this.iCurPg = iCurPg;
    }
    
    public int getCurrentPage() {
        return this.iCurPg<=0?1:this.iCurPg;
    }

    public int getMaxrowPerPage() {
        return this.iAllRowCnt - this.iPerPgLin * (getCurrentPage()-1);
    }

    public int getTotalPageNum() {
    	int totalPage = ((iAllRowCnt - 1) / iPerPgLin) + 1;// 총 Page 수
    	return totalPage;
    }
    public String getCallFun() {
		return callFun;
	}

	public void setCallFun(String callFun) {
		this.callFun = callFun;
	}

	/**
     * 내부사용ICON context path를 받아서 설정파일의 각 icon image path를 설정한다.<br>
     * 참조: setting.properties
     * @param contextPath
     */
    public void setIconContextPath(String contextPath){
       
//    	if(conf == null){
//            conf = ConfigHelper.getConfiguration();
//        }

        //this.setFirstMoveIcon(contextPath+conf.getString("PAGING.BUTTON.BACK.IMG.FIRST"));
        //this.setLastMoveIcon(contextPath+conf.getString("PAGING.BUTTON.BACK.IMG.LAST"));
        //this.setPrevMoveIcon(contextPath+conf.getString("PAGING.BUTTON.BACK.IMG.PREV"));
        //this.setNextMoveIcon(contextPath+conf.getString("PAGING.BUTTON.BACK.IMG.NEXT"));
        
    }
   

    /**
     * 페이징계산에 사용할 전체 Row수
     * @param iAllRowCnt
     */
    public void setAllRowCount(int iAllRowCnt) {
        this.iAllRowCnt = iAllRowCnt;
    }

    private void initVar() {

        if (frstMovTag == null) {
            frstMovTag = FIRST_MOVE_TAG;
        }

        if (lastMovTag == null) {
            lastMovTag = LAST_MOVE_TAG;
        }

        if (prevMovTag == null) {
            prevMovTag = PREV_MOVE_TAG;
        }

        if (nextMovTag == null) {
            nextMovTag = NEXT_MOVE_TAG;
        }

        if (pageNumStartTag == null) {
            pageNumStartTag = PAGE_NUM_START_TAG;
        }

        if (pageNumEndTag == null) {
            pageNumEndTag = PAGE_NUM_END_TAG;
        }

        if(pagingParamName == null){
            pagingParamName = PAGING_PARAM_NAME;
        }

        if(lineHeaderTag == null){
            lineHeaderTag = "";
        }

        if(lineTailTag == null){
            lineTailTag = "";
        }
    }

    public void reset() {
        frstMovTag = null;
        lastMovTag = null;
        prevMovTag = null;
        nextMovTag = null;
        lineHeaderTag = null;
        lineTailTag = null;
        pagingParamName = null;
    }

    /**
     * 페이지 계산 출력.
     */
    public String toString() {

        int printPage = 0;// Index를 출력하기 위한 Page
        StringBuffer outBuf = new StringBuffer();
        int totalPage = ((iAllRowCnt - 1) / iPerPgLin) + 1;// 총 Page 수
        int cPageGrp = ((iCurPg - 1) / iPgGrpCnt);// 현재 Page의 그룹 번호를 계산( 페이지를 iPgGrpCnt개 단위로 출력하기 위해 )
        int tPageGrp = ((totalPage - 1) / iPgGrpCnt);// 총 Page의 그룹 번호를 계산( 페이지를 iPgGrpCnt개 단위로 출력하기 위해 )

        initVar();//변수 초기화

        outBuf.append(lineHeaderTag);

        
        //페이징갯수 조절함수가 존재하면 출력
        adjustLineFun = null;
        
        if(adjustLineFun != null){
        	
	        outBuf.append("<select title='한페이지에 보여줄 검색 수' ").append("onchange=\"").append(adjustLineFun).append("(this.value)\">");
	        outBuf.append("<option value=\"10\"").append(iPerPgLin==10?" selected":"").append("/>10</option>");
	        outBuf.append("<option value=\"20\"").append(iPerPgLin==20?" selected":"").append("/>20</option>");
	        outBuf.append("<option value=\"30\"").append(iPerPgLin==30?" selected":"").append("/>30</option>");
	        outBuf.append("<option value=\"40\"").append(iPerPgLin==40?" selected":"").append("/>40</option>");
	        outBuf.append("<option value=\"50\"").append(iPerPgLin==50?" selected":"").append("/>50</option>");
	        outBuf.append("<option value=\"100\"").append(iPerPgLin==100?" selected":"").append("/>100</option>");
//	        outBuf.append("<option value=\"500\"").append(iPerPgLin==500?" selected":"").append("/>500</option>");
//	        outBuf.append("<option value=\"1000\"").append(iPerPgLin==1000?" selected":"").append("/>1000</option>");
	        
	        //iMaxLine 300
	        if(iMaxLine==300){
	        	outBuf.append("<option value=\"200\"").append(iPerPgLin==200?" selected":"").append("/>200</option>");
	        	outBuf.append("<option value=\"300\"").append(iPerPgLin==300?" selected":"").append("/>300</option>");
	        }
	        //iMaxLine 500
	        if(iMaxLine==500){
	        	outBuf.append("<option value=\"200\"").append(iPerPgLin==200?" selected":"").append("/>200</option>");
	        	outBuf.append("<option value=\"300\"").append(iPerPgLin==300?" selected":"").append("/>300</option>");
	        	outBuf.append("<option value=\"500\"").append(iPerPgLin==500?" selected":"").append("/>500</option>");
	        }
	        //iMaxLine 1000
	        if(iMaxLine==1000){
	        	outBuf.append("<option value=\"200\"").append(iPerPgLin==200?" selected":"").append("/>200</option>");
	        	outBuf.append("<option value=\"300\"").append(iPerPgLin==300?" selected":"").append("/>300</option>");
	        	outBuf.append("<option value=\"500\"").append(iPerPgLin==500?" selected":"").append("/>500</option>");
	        	outBuf.append("<option value=\"1000\"").append(iPerPgLin==1000?" selected":"").append("/>1000</option>");
	        }	        
	        //경우에 따라서 전체보기를 제한할때 사용.
	        if(isAdjustAll ){
		        outBuf.append("<option value=\"").append(iAllRowCnt).append("\"")
		        	.append(iPerPgLin==iAllRowCnt?" selected":"").append("/>전체</option>");
	        }
	        
	        outBuf.append("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
        }
        
        if(cPageGrp != 0 && tPageGrp >0 ){
             outBuf.append("<a href=\"#none\" class=\"imgPage firstPage\" onclick=\"javascript:");
             outBuf.append(callFun);
             outBuf.append("(");
             outBuf.append("1");
             outBuf.append(',');
             outBuf.append(iPerPgLin);
             
             if (maxId != null){
                 outBuf.append(',');
                 outBuf.append("'"+ maxId+"'");
                 outBuf.append(',');
                 outBuf.append("1");
             }
             
             outBuf.append(")\">");
             outBuf.append(frstMovTag);
             outBuf.append("</a>");
         }
        

        // 현재 페이지가 그룹보다 클경우 이전 페이지 그룹의 첫페이지 이동할 수 있게 링크
        if (iCurPg > iPgGrpCnt) {
        	printPage = cPageGrp * iPgGrpCnt;
            outBuf.append("<a href=\"#none\" class=\"imgPage prevPage\" onclick=\"javascript:");
            outBuf.append(callFun);
            outBuf.append("(");
            outBuf.append(printPage);
            outBuf.append(',');
            outBuf.append(iPerPgLin);
            
            if (maxId != null){
                outBuf.append(',');
                outBuf.append("'"+ maxId+ "'");
                outBuf.append(',');
                outBuf.append("1");
            }
            
            outBuf.append(")\">");
            outBuf.append(prevMovTag);
            outBuf.append("</a>");
        }

        // 페이지 목록을 작성한다.
        int pageCnt = (cPageGrp * iPgGrpCnt) + 1;

        while ((pageCnt <= totalPage) && (pageCnt <= (cPageGrp + 1) * iPgGrpCnt)) {
            if (pageCnt == iCurPg) {
            	
                outBuf.append("<a href=\"#none\" class=\"on a_hover\">");
                outBuf.append(pageCnt);
                outBuf.append("</a>");
                
            } else {
            	
                outBuf.append("<a href=\"#none\" class=\"a_hover\" onclick=\"javascript:");
                outBuf.append(callFun);
                outBuf.append("(");
                outBuf.append(pageCnt);
                outBuf.append(',');
                outBuf.append(iPerPgLin);
                
                if (maxId != null){
                    outBuf.append(',');
                    outBuf.append(maxId);
                    outBuf.append(',');
                    outBuf.append("1");
                }
                
                outBuf.append(")\">");
                outBuf.append(pageCnt);
                outBuf.append("</a>");
            }

            pageCnt++;
        }

        // 페이지 그룹이 총 페이지 그룹보다 작을 경우 다음 페이지 그룹으로 이동할 수 있게 링크

        if (cPageGrp < tPageGrp) {
            printPage = (cPageGrp + 1) * iPgGrpCnt + 1;
            outBuf.append("<a href=\"#none\" class=\"imgPage nextPage\" onclick=\"javascript:");
            outBuf.append(callFun);
            outBuf.append("(");
            outBuf.append(printPage);
            outBuf.append(',');
            outBuf.append(iPerPgLin);
            
            if (minId != null){
                outBuf.append(',');
                outBuf.append("'"+ minId + "'");
                outBuf.append(',');
                outBuf.append("1");
            }
            
            outBuf.append(")\">");
            outBuf.append(nextMovTag);
            outBuf.append("</a>");
        }

       //현재 페이지 그룹이 마지막그룹이 아니면 마지막 페이로 이동 하는 버튼
       if ( cPageGrp != tPageGrp && tPageGrp > 0){
            outBuf.append("<a href=\"#none\" class=\"imgPage lastPage\" onclick=\"javascript:");
            outBuf.append(callFun);
            outBuf.append("(");
            outBuf.append(totalPage);
            outBuf.append(',');
            outBuf.append(iPerPgLin);
            
            if (minId != null){
                outBuf.append(',');
                outBuf.append("'"+ minId + "'");
                outBuf.append(',');
                outBuf.append("1");
            }
            
            outBuf.append(")\">");
            outBuf.append(lastMovTag);
            outBuf.append("</a>");
        }
       
       /*사용안함.
       // ▶| 표시
       if (iCurPg == totalPage) {
           //pageListBuffer.append(IMG_LAST) ;
       } else {
           if (bLast) {
               outBuf.append("&nbsp;<a href=\"javascript:");
               outBuf.append(callFun);
               outBuf.append("(");
               outBuf.append(totalPage);
               outBuf.append(',');
               outBuf.append(iPerPgLin);
               outBuf.append(")\">");
               outBuf.append(lastMovTag);
               outBuf.append("</a>&nbsp;");
           } else {
               outBuf.append("&nbsp;<a href=\"javascript:");
               outBuf.append(callFun);
               outBuf.append("(");
               outBuf.append(totalPage);
               outBuf.append(',');
               outBuf.append(iPerPgLin);
               outBuf.append(")\">");
               outBuf.append(lastMovTag);
               outBuf.append("</a>&nbsp;");
           }
       }
       */
        
        outBuf.append(lineTailTag);

        return outBuf.toString();
    }

    public static int getStarRow(int currentPage, int linesPerPage) {
        return getEndRow(currentPage, linesPerPage) - (linesPerPage-1);
    }

    public static int getEndRow(int currentPage, int linesPerPage) {
    	int tempPage = 0;
    	
        if(currentPage <= 0) {
        	tempPage = 1;
		}else{
			tempPage = currentPage;
		}
        
        return tempPage * linesPerPage;
    }
    
    public void setFirstMoveTag(String frstMovTag) {
        this.frstMovTag = frstMovTag;
    }

    public void setLastMoveTag(String lastMovTag) {
        this.lastMovTag = lastMovTag;
    }

    public void setPrevMoveTag(String prevMovTag) {
        this.prevMovTag = prevMovTag;
    }

    public void setNextMoveTag(String nextMovTag) {
        this.nextMovTag = nextMovTag;
    }

    public void setFirstMoveIcon(String frstMovIcon) {
        this.frstMovTag = "<img src='"+frstMovIcon+"' boarder='1' align='absmiddle' alt='First'>";
    }

    public void setLastMoveIcon(String lastMovIcon) {
        this.lastMovTag = "<img src='"+lastMovIcon+"' boarder='1' align='absmiddle' alt='Last'>";
    }

    public void setPrevMoveIcon(String prevMovIcon) {
        this.prevMovTag = "<img src='"+prevMovIcon+"' boarder='1' align='absmiddle' alt='Prev' >";
    }

    public void setNextMoveIcon(String nextMovIcon) {
        this.nextMovTag = "<img src='"+nextMovIcon+"' boarder='1' align='absmiddle' alt='Next'>";
    }

    public void setLineHeader(String lineHeaderTag) {
        this.lineHeaderTag = lineHeaderTag;
    }

    public void setLineTail(String lineTailTag) {
        this.lineTailTag = lineTailTag;
    }

    public void setAdjustLineFunction(String val){
    	adjustLineFun = val;
    }

    public static int calStarRow(int iCurPg, int iPerLin){
        return calEndRow(iCurPg, iPerLin) - (iPerLin-1);
    }

    public static int calEndRow(int iCurPg, int iPerLin){
    	int tempPage = 0;
    	
        if(iCurPg <= 0){
        	tempPage = 1;
        }else{
        	tempPage = iCurPg;
        }
        return tempPage * iPerLin;
    }

    public static String[] calBetweenRow(int iCurPg, int iPerLin){
        int iStRow = calStarRow(iCurPg, iPerLin);
        int iEdRow = calEndRow(iCurPg, iPerLin);
        return new String[]{""+iStRow, ""+iEdRow};
    }
    
    public static String[] calBetweenRowSwap(int iCurPg, int iPerLin){
        int iStRow = calStarRow(iCurPg, iPerLin);
        int iEdRow = calEndRow(iCurPg, iPerLin);
        return new String[]{""+iEdRow, ""+iStRow};
    }
    
    //test main
    /*public static void main(String[] args){
        Pager pager = new Pager("onMovePage", "10", "10");
        pager.setAllRowCount(500);
        pager.setCurrentPage("10");
    }*/

	public boolean isAdjustAll() {
		return isAdjustAll;
	}

	public void setAdjustAll(boolean isAdjustAll) {
		this.isAdjustAll = isAdjustAll;
	}

	public int getIMaxLine() {
		return iMaxLine;
	}

	public void setIMaxLine(int iMaxLine) {
		if(iMaxLine < 300){
			this.iMaxLine = 100;
		}else if(iMaxLine >= 300 && iMaxLine < 500){
			this.iMaxLine = 300;
		}else if(iMaxLine >= 500 && iMaxLine < 1000){
			this.iMaxLine = 500;
		}else{ //iMaxLine >= 1000
			this.iMaxLine = 1000;
		}
	}
	
	public String getCurPerTot(String currentPage){
		StringBuilder sb = new StringBuilder();
		int totalPage = ((iAllRowCnt - 1) / iPerPgLin) + 1;// 총 Page 수
		sb.append(currentPage).append("/").append(String.valueOf(totalPage));
		return sb.toString();
	}
	
	public static String[] getStartAndEndRow(int currentPage, int linesPerPage) {
        int startRow = getStarRow(currentPage, linesPerPage);
        int endRow = getEndRow(currentPage, linesPerPage);
        
        return new String[] {"" + startRow, "" + endRow};
    }
}