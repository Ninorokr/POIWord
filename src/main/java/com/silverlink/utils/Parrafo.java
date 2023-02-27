package com.silverlink.utils;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import java.math.BigInteger;

public class Parrafo {

    XWPFParagraph parrafo;

    public Parrafo(XWPFDocument documento){
        parrafo = documento.createParagraph();

        CTPPr ppr = parrafo.getCTP().addNewPPr();
        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.AUTO);
        spacing.setLine(BigInteger.valueOf(240));
    }

    public Parrafo(XWPFDocument documento, ParagraphAlignment alineacion){
//        parrafo = documento.createParagraph();
//
//        CTPPr ppr = parrafo.getCTP().addNewPPr();
//        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
//        spacing.setAfter(BigInteger.valueOf(0));
//        spacing.setBefore(BigInteger.valueOf(0));
//        spacing.setLineRule(STLineSpacingRule.AUTO);
//        spacing.setLine(BigInteger.valueOf(240));

        this(documento);
        parrafo.setAlignment(alineacion);
    }

    public Parrafo(XWPFDocument documento, ParagraphAlignment alineacion, int indentacionPrimeraLinea, int indentacionIzquierda){
//        parrafo = documento.createParagraph();
//
//        CTPPr ppr = parrafo.getCTP().addNewPPr();
//        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
//        spacing.setAfter(BigInteger.valueOf(0));
//        spacing.setBefore(BigInteger.valueOf(0));
//        spacing.setLineRule(STLineSpacingRule.AUTO);
//        spacing.setLine(BigInteger.valueOf(240));

        this(documento, alineacion);
//        parrafo.setAlignment(alineacion);
        parrafo.setIndentationFirstLine(indentacionPrimeraLinea);
        parrafo.setIndentationLeft(indentacionIzquierda);
//        new Parrafo(documento).getParagraph().setAlignment(alineacion);
    }

    public Parrafo getParrafo(){
        return this;
    }

    public XWPFParagraph getParagraph(){
        return parrafo;
    }

//    public XWPFParagraph getParrafo(){
//        return parrafo;
//    }
}
