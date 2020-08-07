
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransriskScoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransriskScoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transriskv2score" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="qualitativeRank" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="qualitativeRankText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="thinFile" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="populationRank" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *         &lt;element name="populationRankText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Bureau" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="analysis" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="background" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="explanation">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="negative_factors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="factor" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="positive_factors">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="factor" maxOccurs="unbounded">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Debug" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Init" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="RecordHeader" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ProfileDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Totalscr" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="ScoreCard" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Dirty2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="DFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="USplit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="PS014" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="PS018" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="S046" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="ExclCode" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="factor1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="factor2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="factor3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="factor4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="Attributes">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ia0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ia0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ia0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ib0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ib0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ic0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="if0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="if0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="if0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ig0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ig0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ig0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ig0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="p00101" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="p00102" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="p00401" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pf0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pf0401" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00403" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00405" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00409" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00510" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t00802" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t01104" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t01301" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t01302" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t01303" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t0201d" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t0202d" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t0203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="t0301a" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ta0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ta2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tb3001" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf0409" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf1602" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf1901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tf2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tg2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="th0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ti0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tk0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tk0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tk1105" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tk2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tk2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="to3002" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tp2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ts1301" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ts1303" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ts1304" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="tv0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Variables">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="br01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fr01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fr34s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re21s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re29" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fi20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="in01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pf01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bc20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pb01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="ds01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="g960" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="s068" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="lien24" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="chrgoff" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="repoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="jdsuit12" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="agpub36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="unpdjudg" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="allcollc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="collec24" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="collec36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="colxmd36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="trdcl6" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="trdutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="auttrds" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="autoutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="autnutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="rethibal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="rethccl" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fioactth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="fiagblth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="firatio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="firatoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="revratio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkrcrdlt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkrutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkopbal6" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkratth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bktrades" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkagbal" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bkutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="bankmof" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="colinq12" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="colinq" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="retavgbl" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat01" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="scrat03" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat06" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat07" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat08" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat09" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat11" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat25" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat26" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat27" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrat36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbr20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrre03" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrre12" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrre28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrre34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrfi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrin28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrin34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrmt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbc01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrbc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrrt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg017" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg041" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg042" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg043" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg049" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg051" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg059" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg063" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg068" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg091" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg093" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg094" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg103" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs004" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs019" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs043" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs046" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs059" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs062" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs064" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrs115" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg069" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="scrg058" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Recode">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="re_bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_g103" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps141" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_at34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_re03" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_at28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_g051" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps147" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps148" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_br20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_re34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bc20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_at36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_fi20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_in34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps139" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps144" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_at20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_br28" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_fr34" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_fi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_re21" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_s062" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_bc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_mt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_ps087" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re_in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Hybrid">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="atat" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="at28avg" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="atop06" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="atop12" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="atop18" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="atop24" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="br2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="cb30" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="cb3006" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="cb6006" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="g043y" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="in2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pctpbbc" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pctpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="pctre" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                             &lt;element name="re2912" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Dummy">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Dummy1">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;all>
 *                                       &lt;element name="at250001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at250002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at250003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at250004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at260001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at260002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at260003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at270001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at270002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at270003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at280004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at28avg1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at28avg2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at28avg3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at310003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at310004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at340007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at360007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atat0001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atat0002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop0601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop0602" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop1201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop2401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop2402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop2403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc060001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc200002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc200003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc200004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc200005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bc980006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="bi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="br200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="br280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="br280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="br280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="br320001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb300004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb300601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="cb600601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ds350001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ds350002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ds350003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ds350004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="fi200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="fr280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="fr340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0170001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g043y001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0490001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0510007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0630001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0910001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g0910002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g1030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g1030002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g1030003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g1030004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g9600001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g9600002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g9600003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="g9600004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                     &lt;/all>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Dummy2">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;all>
 *                                       &lt;element name="ia010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ia030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ib010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ib010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ib010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ib010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ic010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="if010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="if030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010404" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010405" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ig010406" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="in280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="in310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="in340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="in340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt020001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="p0040107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pb010001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpbbc1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpbbc2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpbbc3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpf001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpf002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpf003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctpf004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pctre001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="pf300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                     &lt;/all>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Dummy3">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;all>
 *                                       &lt;element name="ps011001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps022001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps022002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps036001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps037001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps037002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps037003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps037004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps044001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps044002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps044003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps047001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps047002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps047003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps047004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps048001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps048002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps048003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps048004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps077001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps077002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps077003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps087001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps088001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps088002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps098001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps102001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps107001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps107002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps107003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps111001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps112001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps120001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps120002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps120003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps120004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps134001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps139001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps139002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps139003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps141001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps141002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps141003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps141004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps144001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps147001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps147002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps147003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps148001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps148002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps148003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps148004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps148005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps262001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps262002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps264001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps339001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps339002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re280102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re280103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re280104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re280105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re291201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re291202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re291203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="rt310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="rt310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0040001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0040002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0040003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0190001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0190002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0430002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0430003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0640001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0640002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0640003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0680001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0680002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0710001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0710002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                     &lt;/all>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Dummy4">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;all>
 *                                       &lt;element name="t0040301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0040501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0040502" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0040503" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0040902" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t00510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t00510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t00510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0051001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0051002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0051003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0051004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0080202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0110401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0110402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130303" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130304" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0130305" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0202d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0202d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0202d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0202d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0202d05" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0203d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0203d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0203d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290108" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290109" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290110" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290111" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ta290112" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tb300101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf190101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf190102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf190103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tg270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tg270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tg270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="th090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ti090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk110501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk260102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk260103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk260104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk270104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="to300201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tp270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ts130403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tv090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="at110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="atop1801" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="fi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ia010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="if010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="mt570001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="ps131001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="re210001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0590003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0590004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0620001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0620002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0620003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0620004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0700001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s0700002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s1150001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="s1150002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0201d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0201d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0201d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="t0301a01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf160201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tf160202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                       &lt;element name="tk080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                                     &lt;/all>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransriskScoreType", propOrder = {
    "text",
    "transriskv2Score",
    "qualitativeRank",
    "qualitativeRankText",
    "thinFile",
    "populationRank",
    "populationRankText",
    "bureau",
    "analysis",
    "debug"
})
public class TransriskScoreType {

    @XmlElement(required = true)
    protected String text;
    @XmlElement(name = "transriskv2score")
    protected int transriskv2Score;
    protected short qualitativeRank;
    @XmlElement(required = true)
    protected String qualitativeRankText;
    protected short thinFile;
    protected short populationRank;
    @XmlElement(required = true)
    protected String populationRankText;
    @XmlElement(name = "Bureau", required = true)
    protected String bureau;
    protected TransriskScoreType.Analysis analysis;
    @XmlElement(name = "Debug")
    protected TransriskScoreType.Debug debug;

    /**
     * Gets the value of the text property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the transriskv2Score property.
     *
     */
    public int getTransriskv2Score() {
        return transriskv2Score;
    }

    /**
     * Sets the value of the transriskv2Score property.
     *
     */
    public void setTransriskv2Score(int value) {
        this.transriskv2Score = value;
    }

    /**
     * Gets the value of the qualitativeRank property.
     *
     */
    public short getQualitativeRank() {
        return qualitativeRank;
    }

    /**
     * Sets the value of the qualitativeRank property.
     *
     */
    public void setQualitativeRank(short value) {
        this.qualitativeRank = value;
    }

    /**
     * Gets the value of the qualitativeRankText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getQualitativeRankText() {
        return qualitativeRankText;
    }

    /**
     * Sets the value of the qualitativeRankText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setQualitativeRankText(String value) {
        this.qualitativeRankText = value;
    }

    /**
     * Gets the value of the thinFile property.
     *
     */
    public short getThinFile() {
        return thinFile;
    }

    /**
     * Sets the value of the thinFile property.
     *
     */
    public void setThinFile(short value) {
        this.thinFile = value;
    }

    /**
     * Gets the value of the populationRank property.
     *
     */
    public short getPopulationRank() {
        return populationRank;
    }

    /**
     * Sets the value of the populationRank property.
     *
     */
    public void setPopulationRank(short value) {
        this.populationRank = value;
    }

    /**
     * Gets the value of the populationRankText property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPopulationRankText() {
        return populationRankText;
    }

    /**
     * Sets the value of the populationRankText property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPopulationRankText(String value) {
        this.populationRankText = value;
    }

    /**
     * Gets the value of the bureau property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBureau(String value) {
        this.bureau = value;
    }

    /**
     * Gets the value of the analysis property.
     *
     * @return
     *     possible object is
     *     {@link TransriskScoreType.Analysis }
     *
     */
    public TransriskScoreType.Analysis getAnalysis() {
        return analysis;
    }

    /**
     * Sets the value of the analysis property.
     *
     * @param value
     *     allowed object is
     *     {@link TransriskScoreType.Analysis }
     *
     */
    public void setAnalysis(TransriskScoreType.Analysis value) {
        this.analysis = value;
    }

    /**
     * Gets the value of the debug property.
     *
     * @return
     *     possible object is
     *     {@link TransriskScoreType.Debug }
     *
     */
    public TransriskScoreType.Debug getDebug() {
        return debug;
    }

    /**
     * Sets the value of the debug property.
     *
     * @param value
     *     allowed object is
     *     {@link TransriskScoreType.Debug }
     *
     */
    public void setDebug(TransriskScoreType.Debug value) {
        this.debug = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="background" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="explanation">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="negative_factors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="factor" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="positive_factors">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="factor" maxOccurs="unbounded">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "background",
        "summary",
        "explanation"
    })
    public static class Analysis {

        @XmlElement(required = true)
        protected String background;
        @XmlElement(required = true)
        protected String summary;
        @XmlElement(required = true)
        protected TransriskScoreType.Analysis.Explanation explanation;

        /**
         * Gets the value of the background property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getBackground() {
            return background;
        }

        /**
         * Sets the value of the background property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setBackground(String value) {
            this.background = value;
        }

        /**
         * Gets the value of the summary property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSummary() {
            return summary;
        }

        /**
         * Sets the value of the summary property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSummary(String value) {
            this.summary = value;
        }

        /**
         * Gets the value of the explanation property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Analysis.Explanation }
         *
         */
        public TransriskScoreType.Analysis.Explanation getExplanation() {
            return explanation;
        }

        /**
         * Sets the value of the explanation property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Analysis.Explanation }
         *
         */
        public void setExplanation(TransriskScoreType.Analysis.Explanation value) {
            this.explanation = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="negative_factors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="factor" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="positive_factors">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="factor" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "text",
            "negativeFactors",
            "positiveFactors"
        })
        public static class Explanation {

            @XmlElement(required = true)
            protected String text;
            @XmlElement(name = "negative_factors", required = true)
            protected TransriskScoreType.Analysis.Explanation.NegativeFactors negativeFactors;
            @XmlElement(name = "positive_factors", required = true)
            protected TransriskScoreType.Analysis.Explanation.PositiveFactors positiveFactors;

            /**
             * Gets the value of the text property.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getText() {
                return text;
            }

            /**
             * Sets the value of the text property.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setText(String value) {
                this.text = value;
            }

            /**
             * Gets the value of the negativeFactors property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Analysis.Explanation.NegativeFactors }
             *
             */
            public TransriskScoreType.Analysis.Explanation.NegativeFactors getNegativeFactors() {
                return negativeFactors;
            }

            /**
             * Sets the value of the negativeFactors property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Analysis.Explanation.NegativeFactors }
             *
             */
            public void setNegativeFactors(TransriskScoreType.Analysis.Explanation.NegativeFactors value) {
                this.negativeFactors = value;
            }

            /**
             * Gets the value of the positiveFactors property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Analysis.Explanation.PositiveFactors }
             *
             */
            public TransriskScoreType.Analysis.Explanation.PositiveFactors getPositiveFactors() {
                return positiveFactors;
            }

            /**
             * Sets the value of the positiveFactors property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Analysis.Explanation.PositiveFactors }
             *
             */
            public void setPositiveFactors(TransriskScoreType.Analysis.Explanation.PositiveFactors value) {
                this.positiveFactors = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="factor" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "factor"
            })
            public static class NegativeFactors {

                @XmlElement(required = true)
                protected List<Factor> factor;

                /**
                 * Gets the value of the factor property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the factor property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFactor().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TransriskScoreType.Analysis.Explanation.NegativeFactors.Factor }
                 *
                 *
                 */
                public List<Factor> getFactor() {
                    if (factor == null) {
                        factor = new ArrayList<Factor>();
                    }
                    return this.factor;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 *
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 *
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 *
                 *
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "descriptionText",
                    "statement"
                })
                public static class Factor {

                    @XmlElement(required = true)
                    protected String descriptionText;
                    @XmlElement(required = true)
                    protected String statement;

                    /**
                     * Gets the value of the descriptionText property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getDescriptionText() {
                        return descriptionText;
                    }

                    /**
                     * Sets the value of the descriptionText property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setDescriptionText(String value) {
                        this.descriptionText = value;
                    }

                    /**
                     * Gets the value of the statement property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getStatement() {
                        return statement;
                    }

                    /**
                     * Sets the value of the statement property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setStatement(String value) {
                        this.statement = value;
                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             *
             * <p>The following schema fragment specifies the expected content contained within this class.
             *
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="factor" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             *
             *
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "factor"
            })
            public static class PositiveFactors {

                @XmlElement(required = true)
                protected List<Factor> factor;

                /**
                 * Gets the value of the factor property.
                 *
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the factor property.
                 *
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFactor().add(newItem);
                 * </pre>
                 *
                 *
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link TransriskScoreType.Analysis.Explanation.PositiveFactors.Factor }
                 *
                 *
                 */
                public List<Factor> getFactor() {
                    if (factor == null) {
                        factor = new ArrayList<Factor>();
                    }
                    return this.factor;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 *
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 *
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="descriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="statement" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 *
                 *
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "descriptionText",
                    "statement"
                })
                public static class Factor {

                    @XmlElement(required = true)
                    protected String descriptionText;
                    @XmlElement(required = true)
                    protected String statement;

                    /**
                     * Gets the value of the descriptionText property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getDescriptionText() {
                        return descriptionText;
                    }

                    /**
                     * Sets the value of the descriptionText property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setDescriptionText(String value) {
                        this.descriptionText = value;
                    }

                    /**
                     * Gets the value of the statement property.
                     *
                     * @return
                     *     possible object is
                     *     {@link String }
                     *
                     */
                    public String getStatement() {
                        return statement;
                    }

                    /**
                     * Sets the value of the statement property.
                     *
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *
                     */
                    public void setStatement(String value) {
                        this.statement = value;
                    }

                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Init" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="RecordHeader" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ProfileDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Totalscr" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="Score" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="ScoreCard" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Dirty2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="DFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="USplit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="PS014" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="PS018" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="S046" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="ExclCode" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="factor1" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="factor2" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="factor3" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="factor4" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="Attributes">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ia0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ia0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ia0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ib0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ib0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ic0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="if0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="if0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="if0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ig0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ig0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ig0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ig0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="p00101" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="p00102" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="p00401" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pf0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pf0401" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00403" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00405" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00409" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00510" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t00802" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t01104" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t01301" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t01302" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t01303" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t0201d" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t0202d" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t0203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="t0301a" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ta0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ta2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tb3001" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf0409" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf1602" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf1901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tf2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tg2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="th0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ti0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tk0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tk0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tk1105" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tk2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tk2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="to3002" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tp2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ts1301" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ts1303" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ts1304" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="tv0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Variables">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="br01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fr01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fr34s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re21s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re29" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fi20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="in01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pf01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bc20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pb01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="ds01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="g960" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="s068" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="lien24" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="chrgoff" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="repoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="jdsuit12" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="agpub36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="unpdjudg" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="allcollc" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="collec24" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="collec36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="colxmd36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="trdcl6" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="trdutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="auttrds" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="autoutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="autnutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="rethibal" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="rethccl" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fioactth" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="fiagblth" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="firatio" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="firatoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="revratio" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkrcrdlt" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkrutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkopbal6" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkratth" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bktrades" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkagbal" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bkutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="bankmof" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="colinq12" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="colinq" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="retavgbl" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat01" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                   &lt;element name="scrat03" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat06" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat07" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat08" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat09" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat11" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat25" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat26" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat27" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrat36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbr20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrre03" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrre12" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrre28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrre34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrfi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrin28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrin34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrmt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbc01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrbc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrrt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg017" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg041" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg042" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg043" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg049" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg051" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg059" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg063" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg068" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg091" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg093" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg094" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg103" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs004" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs019" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs043" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs046" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs059" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs062" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs064" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrs115" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg069" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="scrg058" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Recode">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="re_bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_g103" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps141" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_at34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_re03" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_at28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_g051" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps147" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps148" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_br20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_re34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bc20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_at36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_fi20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_in34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps139" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps144" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_at20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_br28" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_fr34" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_fi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_re21" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_s062" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_bc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_mt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_ps087" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re_in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Hybrid">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="atat" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="at28avg" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="atop06" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="atop12" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="atop18" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="atop24" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="br2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="cb30" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="cb3006" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="cb6006" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="g043y" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="in2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pctpbbc" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pctpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="pctre" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                   &lt;element name="re2912" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Dummy">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Dummy1">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;all>
     *                             &lt;element name="at250001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at250002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at250003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at250004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at260001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at260002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at260003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at270001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at270002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at270003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at280004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at28avg1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at28avg2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at28avg3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at310003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at310004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at340007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at360007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atat0001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atat0002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop0601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop0602" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop1201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop2401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop2402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop2403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc060001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc200002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc200003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc200004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc200005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bc980006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="bi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="br200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="br280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="br280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="br280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="br320001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb300004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb300601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="cb600601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ds350001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ds350002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ds350003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ds350004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="fi200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="fr280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="fr340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0170001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g043y001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0490001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0510007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0630001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0910001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g0910002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g1030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g1030002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g1030003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g1030004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g9600001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g9600002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g9600003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="g9600004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                           &lt;/all>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Dummy2">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;all>
     *                             &lt;element name="ia010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ia030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ib010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ib010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ib010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ib010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ic010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="if010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="if030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010404" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010405" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ig010406" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="in280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="in310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="in340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="in340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt020001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="p0040107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pb010001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpbbc1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpbbc2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpbbc3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpf001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpf002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpf003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctpf004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pctre001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="pf300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                           &lt;/all>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Dummy3">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;all>
     *                             &lt;element name="ps011001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps022001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps022002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps036001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps037001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps037002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps037003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps037004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps044001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps044002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps044003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps047001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps047002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps047003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps047004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps048001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps048002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps048003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps048004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps077001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps077002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps077003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps087001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps088001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps088002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps098001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps102001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps107001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps107002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps107003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps111001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps112001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps120001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps120002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps120003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps120004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps134001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps139001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps139002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps139003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps141001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps141002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps141003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps141004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps144001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps147001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps147002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps147003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps148001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps148002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps148003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps148004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps148005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps262001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps262002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps264001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps339001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps339002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re280102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re280103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re280104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re280105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re291201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re291202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re291203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="rt310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="rt310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0040001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0040002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0040003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0190001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0190002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0430002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0430003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0640001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0640002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0640003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0680001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0680002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0710001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0710002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                           &lt;/all>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Dummy4">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;all>
     *                             &lt;element name="t0040301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0040501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0040502" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0040503" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0040902" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t00510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t00510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t00510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0051001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0051002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0051003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0051004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0080202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0110401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0110402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130303" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130304" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0130305" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0202d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0202d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0202d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0202d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0202d05" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0203d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0203d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0203d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290108" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290109" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290110" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290111" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ta290112" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tb300101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf190101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf190102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf190103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tg270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tg270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tg270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="th090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ti090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk110501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk260102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk260103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk260104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk270104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="to300201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tp270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ts130403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tv090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="at110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="atop1801" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="fi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ia010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="if010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="mt570001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="ps131001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="re210001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0590003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0590004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0620001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0620002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0620003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0620004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0700001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s0700002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s1150001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="s1150002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0201d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0201d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0201d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="t0301a01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf160201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tf160202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                             &lt;element name="tk080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *                           &lt;/all>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "init",
        "recordHeader",
        "sequenceNumber",
        "profileDate",
        "totalscr",
        "score",
        "scoreCard",
        "dirty2",
        "dFlag",
        "uSplit",
        "ps014",
        "ps018",
        "s046",
        "exclCode",
        "factor1",
        "factor2",
        "factor3",
        "factor4",
        "attributes",
        "variables",
        "recode",
        "hybrid",
        "dummy"
    })
    public static class Debug {

        @XmlElement(name = "Init")
        protected TransriskScoreType.Debug.Init init;
        @XmlElement(name = "RecordHeader")
        protected TransriskScoreType.Debug.RecordHeader recordHeader;
        @XmlElement(name = "SequenceNumber")
        protected String sequenceNumber;
        @XmlElement(name = "ProfileDate", required = true)
        protected String profileDate;
        @XmlElement(name = "Totalscr")
        protected long totalscr;
        @XmlElement(name = "Score")
        protected long score;
        @XmlElement(name = "ScoreCard", required = true)
        protected String scoreCard;
        @XmlElement(name = "Dirty2")
        protected String dirty2;
        @XmlElement(name = "DFlag")
        protected String dFlag;
        @XmlElement(name = "USplit")
        protected String uSplit;
        @XmlElement(name = "PS014")
        protected Double ps014;
        @XmlElement(name = "PS018")
        protected Double ps018;
        @XmlElement(name = "S046")
        protected Double s046;
        @XmlElement(name = "ExclCode")
        protected Double exclCode;
        protected int factor1;
        protected int factor2;
        protected int factor3;
        protected int factor4;
        @XmlElement(name = "Attributes", required = true)
        protected TransriskScoreType.Debug.Attributes attributes;
        @XmlElement(name = "Variables", required = true)
        protected TransriskScoreType.Debug.Variables variables;
        @XmlElement(name = "Recode", required = true)
        protected TransriskScoreType.Debug.Recode recode;
        @XmlElement(name = "Hybrid", required = true)
        protected TransriskScoreType.Debug.Hybrid hybrid;
        @XmlElement(name = "Dummy", required = true)
        protected TransriskScoreType.Debug.Dummy dummy;

        /**
         * Gets the value of the init property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Init }
         *
         */
        public TransriskScoreType.Debug.Init getInit() {
            return init;
        }

        /**
         * Sets the value of the init property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Init }
         *
         */
        public void setInit(TransriskScoreType.Debug.Init value) {
            this.init = value;
        }

        /**
         * Gets the value of the recordHeader property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.RecordHeader }
         *
         */
        public TransriskScoreType.Debug.RecordHeader getRecordHeader() {
            return recordHeader;
        }

        /**
         * Sets the value of the recordHeader property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.RecordHeader }
         *
         */
        public void setRecordHeader(TransriskScoreType.Debug.RecordHeader value) {
            this.recordHeader = value;
        }

        /**
         * Gets the value of the sequenceNumber property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getSequenceNumber() {
            return sequenceNumber;
        }

        /**
         * Sets the value of the sequenceNumber property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setSequenceNumber(String value) {
            this.sequenceNumber = value;
        }

        /**
         * Gets the value of the profileDate property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getProfileDate() {
            return profileDate;
        }

        /**
         * Sets the value of the profileDate property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setProfileDate(String value) {
            this.profileDate = value;
        }

        /**
         * Gets the value of the totalscr property.
         *
         */
        public long getTotalscr() {
            return totalscr;
        }

        /**
         * Sets the value of the totalscr property.
         *
         */
        public void setTotalscr(long value) {
            this.totalscr = value;
        }

        /**
         * Gets the value of the score property.
         *
         */
        public long getScore() {
            return score;
        }

        /**
         * Sets the value of the score property.
         *
         */
        public void setScore(long value) {
            this.score = value;
        }

        /**
         * Gets the value of the scoreCard property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getScoreCard() {
            return scoreCard;
        }

        /**
         * Sets the value of the scoreCard property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setScoreCard(String value) {
            this.scoreCard = value;
        }

        /**
         * Gets the value of the dirty2 property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDirty2() {
            return dirty2;
        }

        /**
         * Sets the value of the dirty2 property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDirty2(String value) {
            this.dirty2 = value;
        }

        /**
         * Gets the value of the dFlag property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getDFlag() {
            return dFlag;
        }

        /**
         * Sets the value of the dFlag property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setDFlag(String value) {
            this.dFlag = value;
        }

        /**
         * Gets the value of the uSplit property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getUSplit() {
            return uSplit;
        }

        /**
         * Sets the value of the uSplit property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setUSplit(String value) {
            this.uSplit = value;
        }

        /**
         * Gets the value of the ps014 property.
         *
         * @return
         *     possible object is
         *     {@link Double }
         *
         */
        public Double getPS014() {
            return ps014;
        }

        /**
         * Sets the value of the ps014 property.
         *
         * @param value
         *     allowed object is
         *     {@link Double }
         *
         */
        public void setPS014(Double value) {
            this.ps014 = value;
        }

        /**
         * Gets the value of the ps018 property.
         *
         * @return
         *     possible object is
         *     {@link Double }
         *
         */
        public Double getPS018() {
            return ps018;
        }

        /**
         * Sets the value of the ps018 property.
         *
         * @param value
         *     allowed object is
         *     {@link Double }
         *
         */
        public void setPS018(Double value) {
            this.ps018 = value;
        }

        /**
         * Gets the value of the s046 property.
         *
         * @return
         *     possible object is
         *     {@link Double }
         *
         */
        public Double getS046() {
            return s046;
        }

        /**
         * Sets the value of the s046 property.
         *
         * @param value
         *     allowed object is
         *     {@link Double }
         *
         */
        public void setS046(Double value) {
            this.s046 = value;
        }

        /**
         * Gets the value of the exclCode property.
         *
         * @return
         *     possible object is
         *     {@link Double }
         *
         */
        public Double getExclCode() {
            return exclCode;
        }

        /**
         * Sets the value of the exclCode property.
         *
         * @param value
         *     allowed object is
         *     {@link Double }
         *
         */
        public void setExclCode(Double value) {
            this.exclCode = value;
        }

        /**
         * Gets the value of the factor1 property.
         *
         */
        public int getFactor1() {
            return factor1;
        }

        /**
         * Sets the value of the factor1 property.
         *
         */
        public void setFactor1(int value) {
            this.factor1 = value;
        }

        /**
         * Gets the value of the factor2 property.
         *
         */
        public int getFactor2() {
            return factor2;
        }

        /**
         * Sets the value of the factor2 property.
         *
         */
        public void setFactor2(int value) {
            this.factor2 = value;
        }

        /**
         * Gets the value of the factor3 property.
         *
         */
        public int getFactor3() {
            return factor3;
        }

        /**
         * Sets the value of the factor3 property.
         *
         */
        public void setFactor3(int value) {
            this.factor3 = value;
        }

        /**
         * Gets the value of the factor4 property.
         *
         */
        public int getFactor4() {
            return factor4;
        }

        /**
         * Sets the value of the factor4 property.
         *
         */
        public void setFactor4(int value) {
            this.factor4 = value;
        }

        /**
         * Gets the value of the attributes property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Attributes }
         *
         */
        public TransriskScoreType.Debug.Attributes getAttributes() {
            return attributes;
        }

        /**
         * Sets the value of the attributes property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Attributes }
         *
         */
        public void setAttributes(TransriskScoreType.Debug.Attributes value) {
            this.attributes = value;
        }

        /**
         * Gets the value of the variables property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Variables }
         *
         */
        public TransriskScoreType.Debug.Variables getVariables() {
            return variables;
        }

        /**
         * Sets the value of the variables property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Variables }
         *
         */
        public void setVariables(TransriskScoreType.Debug.Variables value) {
            this.variables = value;
        }

        /**
         * Gets the value of the recode property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Recode }
         *
         */
        public TransriskScoreType.Debug.Recode getRecode() {
            return recode;
        }

        /**
         * Sets the value of the recode property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Recode }
         *
         */
        public void setRecode(TransriskScoreType.Debug.Recode value) {
            this.recode = value;
        }

        /**
         * Gets the value of the hybrid property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Hybrid }
         *
         */
        public TransriskScoreType.Debug.Hybrid getHybrid() {
            return hybrid;
        }

        /**
         * Sets the value of the hybrid property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Hybrid }
         *
         */
        public void setHybrid(TransriskScoreType.Debug.Hybrid value) {
            this.hybrid = value;
        }

        /**
         * Gets the value of the dummy property.
         *
         * @return
         *     possible object is
         *     {@link TransriskScoreType.Debug.Dummy }
         *
         */
        public TransriskScoreType.Debug.Dummy getDummy() {
            return dummy;
        }

        /**
         * Sets the value of the dummy property.
         *
         * @param value
         *     allowed object is
         *     {@link TransriskScoreType.Debug.Dummy }
         *
         */
        public void setDummy(TransriskScoreType.Debug.Dummy value) {
            this.dummy = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="ia0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ia0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ia0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ib0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ib0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ic0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="if0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="if0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="if0301" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ig0101" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ig0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ig0103" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ig0104" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="p00101" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="p00102" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="p00401" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pf0102" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pf0401" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00403" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00405" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00409" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00510" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t00802" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t01104" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t01301" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t01302" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t01303" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t0201d" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t0202d" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t0203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="t0301a" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ta0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ta2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tb3001" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf0409" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf1602" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf1901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf203d" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tf2901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tg2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="th0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ti0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tk0801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tk0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tk1105" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tk2601" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tk2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="to3002" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tp2701" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ts1301" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ts1303" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ts1304" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="tv0901" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "ia0102",
            "ia0104",
            "ia0301",
            "ib0103",
            "ib0104",
            "ic0101",
            "if0101",
            "if0102",
            "if0301",
            "ig0101",
            "ig0102",
            "ig0103",
            "ig0104",
            "p00101",
            "p00102",
            "p00401",
            "pf0102",
            "pf0401",
            "t00403",
            "t00405",
            "t00409",
            "t00510",
            "t00801",
            "t00802",
            "t01104",
            "t01301",
            "t01302",
            "t01303",
            "t0201D",
            "t0202D",
            "t0203D",
            "t0301A",
            "ta0801",
            "ta2901",
            "tb3001",
            "tf0409",
            "tf1602",
            "tf1901",
            "tf203D",
            "tf2601",
            "tf2701",
            "tf2901",
            "tg2701",
            "th0901",
            "ti0901",
            "tk0801",
            "tk0901",
            "tk1105",
            "tk2601",
            "tk2701",
            "to3002",
            "tp2701",
            "ts1301",
            "ts1303",
            "ts1304",
            "tv0901"
        })
        public static class Attributes {

            protected double ia0102;
            protected double ia0104;
            protected double ia0301;
            protected double ib0103;
            protected double ib0104;
            protected double ic0101;
            protected double if0101;
            protected double if0102;
            protected double if0301;
            protected double ig0101;
            protected double ig0102;
            protected double ig0103;
            protected double ig0104;
            protected double p00101;
            protected double p00102;
            protected double p00401;
            protected double pf0102;
            protected double pf0401;
            protected double t00403;
            protected double t00405;
            protected double t00409;
            protected double t00510;
            protected double t00801;
            protected double t00802;
            protected double t01104;
            protected double t01301;
            protected double t01302;
            protected double t01303;
            @XmlElement(name = "t0201d")
            protected double t0201D;
            @XmlElement(name = "t0202d")
            protected double t0202D;
            @XmlElement(name = "t0203d")
            protected double t0203D;
            @XmlElement(name = "t0301a")
            protected double t0301A;
            protected double ta0801;
            protected double ta2901;
            protected double tb3001;
            protected double tf0409;
            protected double tf1602;
            protected double tf1901;
            @XmlElement(name = "tf203d")
            protected double tf203D;
            protected double tf2601;
            protected double tf2701;
            protected double tf2901;
            protected double tg2701;
            protected double th0901;
            protected double ti0901;
            protected double tk0801;
            protected double tk0901;
            protected double tk1105;
            protected double tk2601;
            protected double tk2701;
            protected double to3002;
            protected double tp2701;
            protected double ts1301;
            protected double ts1303;
            protected double ts1304;
            protected double tv0901;

            /**
             * Gets the value of the ia0102 property.
             *
             */
            public double getIa0102() {
                return ia0102;
            }

            /**
             * Sets the value of the ia0102 property.
             *
             */
            public void setIa0102(double value) {
                this.ia0102 = value;
            }

            /**
             * Gets the value of the ia0104 property.
             *
             */
            public double getIa0104() {
                return ia0104;
            }

            /**
             * Sets the value of the ia0104 property.
             *
             */
            public void setIa0104(double value) {
                this.ia0104 = value;
            }

            /**
             * Gets the value of the ia0301 property.
             *
             */
            public double getIa0301() {
                return ia0301;
            }

            /**
             * Sets the value of the ia0301 property.
             *
             */
            public void setIa0301(double value) {
                this.ia0301 = value;
            }

            /**
             * Gets the value of the ib0103 property.
             *
             */
            public double getIb0103() {
                return ib0103;
            }

            /**
             * Sets the value of the ib0103 property.
             *
             */
            public void setIb0103(double value) {
                this.ib0103 = value;
            }

            /**
             * Gets the value of the ib0104 property.
             *
             */
            public double getIb0104() {
                return ib0104;
            }

            /**
             * Sets the value of the ib0104 property.
             *
             */
            public void setIb0104(double value) {
                this.ib0104 = value;
            }

            /**
             * Gets the value of the ic0101 property.
             *
             */
            public double getIc0101() {
                return ic0101;
            }

            /**
             * Sets the value of the ic0101 property.
             *
             */
            public void setIc0101(double value) {
                this.ic0101 = value;
            }

            /**
             * Gets the value of the if0101 property.
             *
             */
            public double getIf0101() {
                return if0101;
            }

            /**
             * Sets the value of the if0101 property.
             *
             */
            public void setIf0101(double value) {
                this.if0101 = value;
            }

            /**
             * Gets the value of the if0102 property.
             *
             */
            public double getIf0102() {
                return if0102;
            }

            /**
             * Sets the value of the if0102 property.
             *
             */
            public void setIf0102(double value) {
                this.if0102 = value;
            }

            /**
             * Gets the value of the if0301 property.
             *
             */
            public double getIf0301() {
                return if0301;
            }

            /**
             * Sets the value of the if0301 property.
             *
             */
            public void setIf0301(double value) {
                this.if0301 = value;
            }

            /**
             * Gets the value of the ig0101 property.
             *
             */
            public double getIg0101() {
                return ig0101;
            }

            /**
             * Sets the value of the ig0101 property.
             *
             */
            public void setIg0101(double value) {
                this.ig0101 = value;
            }

            /**
             * Gets the value of the ig0102 property.
             *
             */
            public double getIg0102() {
                return ig0102;
            }

            /**
             * Sets the value of the ig0102 property.
             *
             */
            public void setIg0102(double value) {
                this.ig0102 = value;
            }

            /**
             * Gets the value of the ig0103 property.
             *
             */
            public double getIg0103() {
                return ig0103;
            }

            /**
             * Sets the value of the ig0103 property.
             *
             */
            public void setIg0103(double value) {
                this.ig0103 = value;
            }

            /**
             * Gets the value of the ig0104 property.
             *
             */
            public double getIg0104() {
                return ig0104;
            }

            /**
             * Sets the value of the ig0104 property.
             *
             */
            public void setIg0104(double value) {
                this.ig0104 = value;
            }

            /**
             * Gets the value of the p00101 property.
             *
             */
            public double getP00101() {
                return p00101;
            }

            /**
             * Sets the value of the p00101 property.
             *
             */
            public void setP00101(double value) {
                this.p00101 = value;
            }

            /**
             * Gets the value of the p00102 property.
             *
             */
            public double getP00102() {
                return p00102;
            }

            /**
             * Sets the value of the p00102 property.
             *
             */
            public void setP00102(double value) {
                this.p00102 = value;
            }

            /**
             * Gets the value of the p00401 property.
             *
             */
            public double getP00401() {
                return p00401;
            }

            /**
             * Sets the value of the p00401 property.
             *
             */
            public void setP00401(double value) {
                this.p00401 = value;
            }

            /**
             * Gets the value of the pf0102 property.
             *
             */
            public double getPf0102() {
                return pf0102;
            }

            /**
             * Sets the value of the pf0102 property.
             *
             */
            public void setPf0102(double value) {
                this.pf0102 = value;
            }

            /**
             * Gets the value of the pf0401 property.
             *
             */
            public double getPf0401() {
                return pf0401;
            }

            /**
             * Sets the value of the pf0401 property.
             *
             */
            public void setPf0401(double value) {
                this.pf0401 = value;
            }

            /**
             * Gets the value of the t00403 property.
             *
             */
            public double getT00403() {
                return t00403;
            }

            /**
             * Sets the value of the t00403 property.
             *
             */
            public void setT00403(double value) {
                this.t00403 = value;
            }

            /**
             * Gets the value of the t00405 property.
             *
             */
            public double getT00405() {
                return t00405;
            }

            /**
             * Sets the value of the t00405 property.
             *
             */
            public void setT00405(double value) {
                this.t00405 = value;
            }

            /**
             * Gets the value of the t00409 property.
             *
             */
            public double getT00409() {
                return t00409;
            }

            /**
             * Sets the value of the t00409 property.
             *
             */
            public void setT00409(double value) {
                this.t00409 = value;
            }

            /**
             * Gets the value of the t00510 property.
             *
             */
            public double getT00510() {
                return t00510;
            }

            /**
             * Sets the value of the t00510 property.
             *
             */
            public void setT00510(double value) {
                this.t00510 = value;
            }

            /**
             * Gets the value of the t00801 property.
             *
             */
            public double getT00801() {
                return t00801;
            }

            /**
             * Sets the value of the t00801 property.
             *
             */
            public void setT00801(double value) {
                this.t00801 = value;
            }

            /**
             * Gets the value of the t00802 property.
             *
             */
            public double getT00802() {
                return t00802;
            }

            /**
             * Sets the value of the t00802 property.
             *
             */
            public void setT00802(double value) {
                this.t00802 = value;
            }

            /**
             * Gets the value of the t01104 property.
             *
             */
            public double getT01104() {
                return t01104;
            }

            /**
             * Sets the value of the t01104 property.
             *
             */
            public void setT01104(double value) {
                this.t01104 = value;
            }

            /**
             * Gets the value of the t01301 property.
             *
             */
            public double getT01301() {
                return t01301;
            }

            /**
             * Sets the value of the t01301 property.
             *
             */
            public void setT01301(double value) {
                this.t01301 = value;
            }

            /**
             * Gets the value of the t01302 property.
             *
             */
            public double getT01302() {
                return t01302;
            }

            /**
             * Sets the value of the t01302 property.
             *
             */
            public void setT01302(double value) {
                this.t01302 = value;
            }

            /**
             * Gets the value of the t01303 property.
             *
             */
            public double getT01303() {
                return t01303;
            }

            /**
             * Sets the value of the t01303 property.
             *
             */
            public void setT01303(double value) {
                this.t01303 = value;
            }

            /**
             * Gets the value of the t0201D property.
             *
             */
            public double getT0201D() {
                return t0201D;
            }

            /**
             * Sets the value of the t0201D property.
             *
             */
            public void setT0201D(double value) {
                this.t0201D = value;
            }

            /**
             * Gets the value of the t0202D property.
             *
             */
            public double getT0202D() {
                return t0202D;
            }

            /**
             * Sets the value of the t0202D property.
             *
             */
            public void setT0202D(double value) {
                this.t0202D = value;
            }

            /**
             * Gets the value of the t0203D property.
             *
             */
            public double getT0203D() {
                return t0203D;
            }

            /**
             * Sets the value of the t0203D property.
             *
             */
            public void setT0203D(double value) {
                this.t0203D = value;
            }

            /**
             * Gets the value of the t0301A property.
             *
             */
            public double getT0301A() {
                return t0301A;
            }

            /**
             * Sets the value of the t0301A property.
             *
             */
            public void setT0301A(double value) {
                this.t0301A = value;
            }

            /**
             * Gets the value of the ta0801 property.
             *
             */
            public double getTa0801() {
                return ta0801;
            }

            /**
             * Sets the value of the ta0801 property.
             *
             */
            public void setTa0801(double value) {
                this.ta0801 = value;
            }

            /**
             * Gets the value of the ta2901 property.
             *
             */
            public double getTa2901() {
                return ta2901;
            }

            /**
             * Sets the value of the ta2901 property.
             *
             */
            public void setTa2901(double value) {
                this.ta2901 = value;
            }

            /**
             * Gets the value of the tb3001 property.
             *
             */
            public double getTb3001() {
                return tb3001;
            }

            /**
             * Sets the value of the tb3001 property.
             *
             */
            public void setTb3001(double value) {
                this.tb3001 = value;
            }

            /**
             * Gets the value of the tf0409 property.
             *
             */
            public double getTf0409() {
                return tf0409;
            }

            /**
             * Sets the value of the tf0409 property.
             *
             */
            public void setTf0409(double value) {
                this.tf0409 = value;
            }

            /**
             * Gets the value of the tf1602 property.
             *
             */
            public double getTf1602() {
                return tf1602;
            }

            /**
             * Sets the value of the tf1602 property.
             *
             */
            public void setTf1602(double value) {
                this.tf1602 = value;
            }

            /**
             * Gets the value of the tf1901 property.
             *
             */
            public double getTf1901() {
                return tf1901;
            }

            /**
             * Sets the value of the tf1901 property.
             *
             */
            public void setTf1901(double value) {
                this.tf1901 = value;
            }

            /**
             * Gets the value of the tf203D property.
             *
             */
            public double getTf203D() {
                return tf203D;
            }

            /**
             * Sets the value of the tf203D property.
             *
             */
            public void setTf203D(double value) {
                this.tf203D = value;
            }

            /**
             * Gets the value of the tf2601 property.
             *
             */
            public double getTf2601() {
                return tf2601;
            }

            /**
             * Sets the value of the tf2601 property.
             *
             */
            public void setTf2601(double value) {
                this.tf2601 = value;
            }

            /**
             * Gets the value of the tf2701 property.
             *
             */
            public double getTf2701() {
                return tf2701;
            }

            /**
             * Sets the value of the tf2701 property.
             *
             */
            public void setTf2701(double value) {
                this.tf2701 = value;
            }

            /**
             * Gets the value of the tf2901 property.
             *
             */
            public double getTf2901() {
                return tf2901;
            }

            /**
             * Sets the value of the tf2901 property.
             *
             */
            public void setTf2901(double value) {
                this.tf2901 = value;
            }

            /**
             * Gets the value of the tg2701 property.
             *
             */
            public double getTg2701() {
                return tg2701;
            }

            /**
             * Sets the value of the tg2701 property.
             *
             */
            public void setTg2701(double value) {
                this.tg2701 = value;
            }

            /**
             * Gets the value of the th0901 property.
             *
             */
            public double getTh0901() {
                return th0901;
            }

            /**
             * Sets the value of the th0901 property.
             *
             */
            public void setTh0901(double value) {
                this.th0901 = value;
            }

            /**
             * Gets the value of the ti0901 property.
             *
             */
            public double getTi0901() {
                return ti0901;
            }

            /**
             * Sets the value of the ti0901 property.
             *
             */
            public void setTi0901(double value) {
                this.ti0901 = value;
            }

            /**
             * Gets the value of the tk0801 property.
             *
             */
            public double getTk0801() {
                return tk0801;
            }

            /**
             * Sets the value of the tk0801 property.
             *
             */
            public void setTk0801(double value) {
                this.tk0801 = value;
            }

            /**
             * Gets the value of the tk0901 property.
             *
             */
            public double getTk0901() {
                return tk0901;
            }

            /**
             * Sets the value of the tk0901 property.
             *
             */
            public void setTk0901(double value) {
                this.tk0901 = value;
            }

            /**
             * Gets the value of the tk1105 property.
             *
             */
            public double getTk1105() {
                return tk1105;
            }

            /**
             * Sets the value of the tk1105 property.
             *
             */
            public void setTk1105(double value) {
                this.tk1105 = value;
            }

            /**
             * Gets the value of the tk2601 property.
             *
             */
            public double getTk2601() {
                return tk2601;
            }

            /**
             * Sets the value of the tk2601 property.
             *
             */
            public void setTk2601(double value) {
                this.tk2601 = value;
            }

            /**
             * Gets the value of the tk2701 property.
             *
             */
            public double getTk2701() {
                return tk2701;
            }

            /**
             * Sets the value of the tk2701 property.
             *
             */
            public void setTk2701(double value) {
                this.tk2701 = value;
            }

            /**
             * Gets the value of the to3002 property.
             *
             */
            public double getTo3002() {
                return to3002;
            }

            /**
             * Sets the value of the to3002 property.
             *
             */
            public void setTo3002(double value) {
                this.to3002 = value;
            }

            /**
             * Gets the value of the tp2701 property.
             *
             */
            public double getTp2701() {
                return tp2701;
            }

            /**
             * Sets the value of the tp2701 property.
             *
             */
            public void setTp2701(double value) {
                this.tp2701 = value;
            }

            /**
             * Gets the value of the ts1301 property.
             *
             */
            public double getTs1301() {
                return ts1301;
            }

            /**
             * Sets the value of the ts1301 property.
             *
             */
            public void setTs1301(double value) {
                this.ts1301 = value;
            }

            /**
             * Gets the value of the ts1303 property.
             *
             */
            public double getTs1303() {
                return ts1303;
            }

            /**
             * Sets the value of the ts1303 property.
             *
             */
            public void setTs1303(double value) {
                this.ts1303 = value;
            }

            /**
             * Gets the value of the ts1304 property.
             *
             */
            public double getTs1304() {
                return ts1304;
            }

            /**
             * Sets the value of the ts1304 property.
             *
             */
            public void setTs1304(double value) {
                this.ts1304 = value;
            }

            /**
             * Gets the value of the tv0901 property.
             *
             */
            public double getTv0901() {
                return tv0901;
            }

            /**
             * Sets the value of the tv0901 property.
             *
             */
            public void setTv0901(double value) {
                this.tv0901 = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         *
         * <p>The following schema fragment specifies the expected content contained within this class.
         *
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Dummy1">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;all>
         *                   &lt;element name="at250001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at250002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at250003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at250004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at260001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at260002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at260003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at270001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at270002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at270003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at280004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at28avg1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at28avg2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at28avg3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at310003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at310004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at340007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at360007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atat0001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atat0002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop0601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop0602" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop1201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop2401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop2402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop2403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc060001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc200002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc200003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc200004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc200005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bc980006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="bi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="br200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="br280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="br280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="br280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="br320001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb300004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb300601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="cb600601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ds350001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ds350002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ds350003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ds350004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="fi200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="fr280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="fr340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0170001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g043y001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0490001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0510007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0630001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0910001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g0910002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g1030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g1030002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g1030003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g1030004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g9600001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g9600002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g9600003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="g9600004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                 &lt;/all>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Dummy2">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;all>
         *                   &lt;element name="ia010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ia030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ib010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ib010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ib010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ib010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ic010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="if010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="if030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010404" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010405" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ig010406" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="in280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="in310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="in340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="in340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt020001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="p0040107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pb010001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpbbc1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpbbc2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpbbc3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpf001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpf002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpf003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctpf004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pctre001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="pf300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                 &lt;/all>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Dummy3">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;all>
         *                   &lt;element name="ps011001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps022001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps022002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps036001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps037001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps037002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps037003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps037004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps044001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps044002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps044003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps047001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps047002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps047003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps047004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps048001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps048002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps048003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps048004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps077001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps077002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps077003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps087001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps088001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps088002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps098001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps102001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps107001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps107002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps107003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps111001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps112001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps120001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps120002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps120003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps120004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps134001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps139001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps139002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps139003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps141001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps141002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps141003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps141004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps144001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps147001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps147002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps147003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps148001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps148002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps148003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps148004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps148005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps262001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps262002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps264001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps339001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps339002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re280102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re280103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re280104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re280105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re291201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re291202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re291203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="rt310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="rt310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0040001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0040002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0040003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0190001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0190002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0430002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0430003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0640001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0640002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0640003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0680001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0680002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0710001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0710002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                 &lt;/all>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Dummy4">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;all>
         *                   &lt;element name="t0040301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0040501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0040502" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0040503" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0040902" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t00510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t00510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t00510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0051001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0051002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0051003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0051004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0080202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0110401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0110402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130303" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130304" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0130305" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0202d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0202d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0202d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0202d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0202d05" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0203d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0203d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0203d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290108" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290109" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290110" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290111" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ta290112" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tb300101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf190101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf190102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf190103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tg270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tg270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tg270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="th090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ti090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk110501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk260102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk260103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk260104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk270104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="to300201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tp270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ts130403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tv090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="at110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="atop1801" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="fi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ia010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="if010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="mt570001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="ps131001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="re210001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0590003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0590004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0620001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0620002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0620003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0620004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0700001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s0700002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s1150001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="s1150002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0201d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0201d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0201d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="t0301a01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf160201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tf160202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                   &lt;element name="tk080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
         *                 &lt;/all>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "dummy1",
            "dummy2",
            "dummy3",
            "dummy4"
        })
        public static class Dummy {

            @XmlElement(name = "Dummy1", required = true)
            protected TransriskScoreType.Debug.Dummy.Dummy1 dummy1;
            @XmlElement(name = "Dummy2", required = true)
            protected TransriskScoreType.Debug.Dummy.Dummy2 dummy2;
            @XmlElement(name = "Dummy3", required = true)
            protected TransriskScoreType.Debug.Dummy.Dummy3 dummy3;
            @XmlElement(name = "Dummy4", required = true)
            protected TransriskScoreType.Debug.Dummy.Dummy4 dummy4;

            /**
             * Gets the value of the dummy1 property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy1 }
             *
             */
            public TransriskScoreType.Debug.Dummy.Dummy1 getDummy1() {
                return dummy1;
            }

            /**
             * Sets the value of the dummy1 property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy1 }
             *
             */
            public void setDummy1(TransriskScoreType.Debug.Dummy.Dummy1 value) {
                this.dummy1 = value;
            }

            /**
             * Gets the value of the dummy2 property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy2 }
             *
             */
            public TransriskScoreType.Debug.Dummy.Dummy2 getDummy2() {
                return dummy2;
            }

            /**
             * Sets the value of the dummy2 property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy2 }
             *
             */
            public void setDummy2(TransriskScoreType.Debug.Dummy.Dummy2 value) {
                this.dummy2 = value;
            }

            /**
             * Gets the value of the dummy3 property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy3 }
             *
             */
            public TransriskScoreType.Debug.Dummy.Dummy3 getDummy3() {
                return dummy3;
            }

            /**
             * Sets the value of the dummy3 property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy3 }
             *
             */
            public void setDummy3(TransriskScoreType.Debug.Dummy.Dummy3 value) {
                this.dummy3 = value;
            }

            /**
             * Gets the value of the dummy4 property.
             *
             * @return
             *     possible object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy4 }
             *
             */
            public TransriskScoreType.Debug.Dummy.Dummy4 getDummy4() {
                return dummy4;
            }

            /**
             * Sets the value of the dummy4 property.
             *
             * @param value
             *     allowed object is
             *     {@link TransriskScoreType.Debug.Dummy.Dummy4 }
             *
             */
            public void setDummy4(TransriskScoreType.Debug.Dummy.Dummy4 value) {
                this.dummy4 = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;all>
             *         &lt;element name="at250001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at250002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at250003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at250004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at260001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at260002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at260003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at270001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at270002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at270003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at280004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at28avg1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at28avg2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at28avg3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at310003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at310004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at340007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at360007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atat0001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atat0002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop0601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop0602" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop1201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop2401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop2402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop2403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc060001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc200002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc200003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc200004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc200005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bc980006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="bi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="br200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="br280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="br280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="br280003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="br320001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb300004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb300601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="cb600601" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ds350001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ds350002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ds350003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ds350004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="fi200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="fr280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="fr340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0170001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g043y001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0490001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510006" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0510007" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0630001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0910001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g0910002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g1030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g1030002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g1030003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g1030004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g9600001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g9600002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g9600003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="g9600004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *       &lt;/all>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {

            })
            public static class Dummy1 {

                protected Double at250001;
                protected Double at250002;
                protected Double at250003;
                protected Double at250004;
                protected Double at260001;
                protected Double at260002;
                protected Double at260003;
                protected Double at270001;
                protected Double at270002;
                protected Double at270003;
                protected Double at280001;
                protected Double at280002;
                protected Double at280003;
                protected Double at280004;
                @XmlElement(name = "at28avg1")
                protected Double at28Avg1;
                @XmlElement(name = "at28avg2")
                protected Double at28Avg2;
                @XmlElement(name = "at28avg3")
                protected Double at28Avg3;
                protected Double at310001;
                protected Double at310002;
                protected Double at310003;
                protected Double at310004;
                protected Double at340001;
                protected Double at340002;
                protected Double at340003;
                protected Double at340004;
                protected Double at340005;
                protected Double at340006;
                protected Double at340007;
                protected Double at360001;
                protected Double at360002;
                protected Double at360003;
                protected Double at360004;
                protected Double at360005;
                protected Double at360006;
                protected Double at360007;
                protected Double atat0001;
                protected Double atat0002;
                protected Double atop0601;
                protected Double atop0602;
                protected Double atop1201;
                protected Double atop2401;
                protected Double atop2402;
                protected Double atop2403;
                protected Double bc060001;
                protected Double bc200001;
                protected Double bc200002;
                protected Double bc200003;
                protected Double bc200004;
                protected Double bc200005;
                protected Double bc280001;
                protected Double bc280002;
                protected Double bc300001;
                protected Double bc360001;
                protected Double bc980001;
                protected Double bc980002;
                protected Double bc980003;
                protected Double bc980004;
                protected Double bc980005;
                protected Double bc980006;
                protected Double bi360001;
                protected Double br200001;
                protected Double br280001;
                protected Double br280002;
                protected Double br280003;
                protected Double br320001;
                protected Double cb300001;
                protected Double cb300002;
                protected Double cb300003;
                protected Double cb300004;
                protected Double cb300601;
                protected Double cb600601;
                protected Double ds350001;
                protected Double ds350002;
                protected Double ds350003;
                protected Double ds350004;
                protected Double fi200001;
                protected Double fr280001;
                protected Double fr340001;
                protected Double g0170001;
                protected Double g0430001;
                @XmlElement(name = "g043y001")
                protected Double g043Y001;
                protected Double g0490001;
                protected Double g0510001;
                protected Double g0510002;
                protected Double g0510003;
                protected Double g0510004;
                protected Double g0510005;
                protected Double g0510006;
                protected Double g0510007;
                protected Double g0590001;
                protected Double g0590002;
                protected Double g0630001;
                protected Double g0910001;
                protected Double g0910002;
                protected Double g1030001;
                protected Double g1030002;
                protected Double g1030003;
                protected Double g1030004;
                protected Double g9600001;
                protected Double g9600002;
                protected Double g9600003;
                protected Double g9600004;

                /**
                 * Gets the value of the at250001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt250001() {
                    return at250001;
                }

                /**
                 * Sets the value of the at250001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt250001(Double value) {
                    this.at250001 = value;
                }

                /**
                 * Gets the value of the at250002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt250002() {
                    return at250002;
                }

                /**
                 * Sets the value of the at250002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt250002(Double value) {
                    this.at250002 = value;
                }

                /**
                 * Gets the value of the at250003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt250003() {
                    return at250003;
                }

                /**
                 * Sets the value of the at250003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt250003(Double value) {
                    this.at250003 = value;
                }

                /**
                 * Gets the value of the at250004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt250004() {
                    return at250004;
                }

                /**
                 * Sets the value of the at250004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt250004(Double value) {
                    this.at250004 = value;
                }

                /**
                 * Gets the value of the at260001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt260001() {
                    return at260001;
                }

                /**
                 * Sets the value of the at260001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt260001(Double value) {
                    this.at260001 = value;
                }

                /**
                 * Gets the value of the at260002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt260002() {
                    return at260002;
                }

                /**
                 * Sets the value of the at260002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt260002(Double value) {
                    this.at260002 = value;
                }

                /**
                 * Gets the value of the at260003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt260003() {
                    return at260003;
                }

                /**
                 * Sets the value of the at260003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt260003(Double value) {
                    this.at260003 = value;
                }

                /**
                 * Gets the value of the at270001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt270001() {
                    return at270001;
                }

                /**
                 * Sets the value of the at270001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt270001(Double value) {
                    this.at270001 = value;
                }

                /**
                 * Gets the value of the at270002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt270002() {
                    return at270002;
                }

                /**
                 * Sets the value of the at270002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt270002(Double value) {
                    this.at270002 = value;
                }

                /**
                 * Gets the value of the at270003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt270003() {
                    return at270003;
                }

                /**
                 * Sets the value of the at270003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt270003(Double value) {
                    this.at270003 = value;
                }

                /**
                 * Gets the value of the at280001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt280001() {
                    return at280001;
                }

                /**
                 * Sets the value of the at280001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt280001(Double value) {
                    this.at280001 = value;
                }

                /**
                 * Gets the value of the at280002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt280002() {
                    return at280002;
                }

                /**
                 * Sets the value of the at280002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt280002(Double value) {
                    this.at280002 = value;
                }

                /**
                 * Gets the value of the at280003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt280003() {
                    return at280003;
                }

                /**
                 * Sets the value of the at280003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt280003(Double value) {
                    this.at280003 = value;
                }

                /**
                 * Gets the value of the at280004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt280004() {
                    return at280004;
                }

                /**
                 * Sets the value of the at280004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt280004(Double value) {
                    this.at280004 = value;
                }

                /**
                 * Gets the value of the at28Avg1 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt28Avg1() {
                    return at28Avg1;
                }

                /**
                 * Sets the value of the at28Avg1 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt28Avg1(Double value) {
                    this.at28Avg1 = value;
                }

                /**
                 * Gets the value of the at28Avg2 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt28Avg2() {
                    return at28Avg2;
                }

                /**
                 * Sets the value of the at28Avg2 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt28Avg2(Double value) {
                    this.at28Avg2 = value;
                }

                /**
                 * Gets the value of the at28Avg3 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt28Avg3() {
                    return at28Avg3;
                }

                /**
                 * Sets the value of the at28Avg3 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt28Avg3(Double value) {
                    this.at28Avg3 = value;
                }

                /**
                 * Gets the value of the at310001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt310001() {
                    return at310001;
                }

                /**
                 * Sets the value of the at310001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt310001(Double value) {
                    this.at310001 = value;
                }

                /**
                 * Gets the value of the at310002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt310002() {
                    return at310002;
                }

                /**
                 * Sets the value of the at310002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt310002(Double value) {
                    this.at310002 = value;
                }

                /**
                 * Gets the value of the at310003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt310003() {
                    return at310003;
                }

                /**
                 * Sets the value of the at310003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt310003(Double value) {
                    this.at310003 = value;
                }

                /**
                 * Gets the value of the at310004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt310004() {
                    return at310004;
                }

                /**
                 * Sets the value of the at310004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt310004(Double value) {
                    this.at310004 = value;
                }

                /**
                 * Gets the value of the at340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340001() {
                    return at340001;
                }

                /**
                 * Sets the value of the at340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340001(Double value) {
                    this.at340001 = value;
                }

                /**
                 * Gets the value of the at340002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340002() {
                    return at340002;
                }

                /**
                 * Sets the value of the at340002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340002(Double value) {
                    this.at340002 = value;
                }

                /**
                 * Gets the value of the at340003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340003() {
                    return at340003;
                }

                /**
                 * Sets the value of the at340003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340003(Double value) {
                    this.at340003 = value;
                }

                /**
                 * Gets the value of the at340004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340004() {
                    return at340004;
                }

                /**
                 * Sets the value of the at340004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340004(Double value) {
                    this.at340004 = value;
                }

                /**
                 * Gets the value of the at340005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340005() {
                    return at340005;
                }

                /**
                 * Sets the value of the at340005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340005(Double value) {
                    this.at340005 = value;
                }

                /**
                 * Gets the value of the at340006 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340006() {
                    return at340006;
                }

                /**
                 * Sets the value of the at340006 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340006(Double value) {
                    this.at340006 = value;
                }

                /**
                 * Gets the value of the at340007 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt340007() {
                    return at340007;
                }

                /**
                 * Sets the value of the at340007 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt340007(Double value) {
                    this.at340007 = value;
                }

                /**
                 * Gets the value of the at360001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360001() {
                    return at360001;
                }

                /**
                 * Sets the value of the at360001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360001(Double value) {
                    this.at360001 = value;
                }

                /**
                 * Gets the value of the at360002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360002() {
                    return at360002;
                }

                /**
                 * Sets the value of the at360002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360002(Double value) {
                    this.at360002 = value;
                }

                /**
                 * Gets the value of the at360003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360003() {
                    return at360003;
                }

                /**
                 * Sets the value of the at360003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360003(Double value) {
                    this.at360003 = value;
                }

                /**
                 * Gets the value of the at360004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360004() {
                    return at360004;
                }

                /**
                 * Sets the value of the at360004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360004(Double value) {
                    this.at360004 = value;
                }

                /**
                 * Gets the value of the at360005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360005() {
                    return at360005;
                }

                /**
                 * Sets the value of the at360005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360005(Double value) {
                    this.at360005 = value;
                }

                /**
                 * Gets the value of the at360006 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360006() {
                    return at360006;
                }

                /**
                 * Sets the value of the at360006 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360006(Double value) {
                    this.at360006 = value;
                }

                /**
                 * Gets the value of the at360007 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt360007() {
                    return at360007;
                }

                /**
                 * Sets the value of the at360007 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt360007(Double value) {
                    this.at360007 = value;
                }

                /**
                 * Gets the value of the atat0001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtat0001() {
                    return atat0001;
                }

                /**
                 * Sets the value of the atat0001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtat0001(Double value) {
                    this.atat0001 = value;
                }

                /**
                 * Gets the value of the atat0002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtat0002() {
                    return atat0002;
                }

                /**
                 * Sets the value of the atat0002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtat0002(Double value) {
                    this.atat0002 = value;
                }

                /**
                 * Gets the value of the atop0601 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop0601() {
                    return atop0601;
                }

                /**
                 * Sets the value of the atop0601 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop0601(Double value) {
                    this.atop0601 = value;
                }

                /**
                 * Gets the value of the atop0602 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop0602() {
                    return atop0602;
                }

                /**
                 * Sets the value of the atop0602 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop0602(Double value) {
                    this.atop0602 = value;
                }

                /**
                 * Gets the value of the atop1201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop1201() {
                    return atop1201;
                }

                /**
                 * Sets the value of the atop1201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop1201(Double value) {
                    this.atop1201 = value;
                }

                /**
                 * Gets the value of the atop2401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop2401() {
                    return atop2401;
                }

                /**
                 * Sets the value of the atop2401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop2401(Double value) {
                    this.atop2401 = value;
                }

                /**
                 * Gets the value of the atop2402 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop2402() {
                    return atop2402;
                }

                /**
                 * Sets the value of the atop2402 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop2402(Double value) {
                    this.atop2402 = value;
                }

                /**
                 * Gets the value of the atop2403 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop2403() {
                    return atop2403;
                }

                /**
                 * Sets the value of the atop2403 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop2403(Double value) {
                    this.atop2403 = value;
                }

                /**
                 * Gets the value of the bc060001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc060001() {
                    return bc060001;
                }

                /**
                 * Sets the value of the bc060001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc060001(Double value) {
                    this.bc060001 = value;
                }

                /**
                 * Gets the value of the bc200001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc200001() {
                    return bc200001;
                }

                /**
                 * Sets the value of the bc200001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc200001(Double value) {
                    this.bc200001 = value;
                }

                /**
                 * Gets the value of the bc200002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc200002() {
                    return bc200002;
                }

                /**
                 * Sets the value of the bc200002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc200002(Double value) {
                    this.bc200002 = value;
                }

                /**
                 * Gets the value of the bc200003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc200003() {
                    return bc200003;
                }

                /**
                 * Sets the value of the bc200003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc200003(Double value) {
                    this.bc200003 = value;
                }

                /**
                 * Gets the value of the bc200004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc200004() {
                    return bc200004;
                }

                /**
                 * Sets the value of the bc200004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc200004(Double value) {
                    this.bc200004 = value;
                }

                /**
                 * Gets the value of the bc200005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc200005() {
                    return bc200005;
                }

                /**
                 * Sets the value of the bc200005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc200005(Double value) {
                    this.bc200005 = value;
                }

                /**
                 * Gets the value of the bc280001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc280001() {
                    return bc280001;
                }

                /**
                 * Sets the value of the bc280001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc280001(Double value) {
                    this.bc280001 = value;
                }

                /**
                 * Gets the value of the bc280002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc280002() {
                    return bc280002;
                }

                /**
                 * Sets the value of the bc280002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc280002(Double value) {
                    this.bc280002 = value;
                }

                /**
                 * Gets the value of the bc300001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc300001() {
                    return bc300001;
                }

                /**
                 * Sets the value of the bc300001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc300001(Double value) {
                    this.bc300001 = value;
                }

                /**
                 * Gets the value of the bc360001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc360001() {
                    return bc360001;
                }

                /**
                 * Sets the value of the bc360001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc360001(Double value) {
                    this.bc360001 = value;
                }

                /**
                 * Gets the value of the bc980001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980001() {
                    return bc980001;
                }

                /**
                 * Sets the value of the bc980001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980001(Double value) {
                    this.bc980001 = value;
                }

                /**
                 * Gets the value of the bc980002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980002() {
                    return bc980002;
                }

                /**
                 * Sets the value of the bc980002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980002(Double value) {
                    this.bc980002 = value;
                }

                /**
                 * Gets the value of the bc980003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980003() {
                    return bc980003;
                }

                /**
                 * Sets the value of the bc980003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980003(Double value) {
                    this.bc980003 = value;
                }

                /**
                 * Gets the value of the bc980004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980004() {
                    return bc980004;
                }

                /**
                 * Sets the value of the bc980004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980004(Double value) {
                    this.bc980004 = value;
                }

                /**
                 * Gets the value of the bc980005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980005() {
                    return bc980005;
                }

                /**
                 * Sets the value of the bc980005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980005(Double value) {
                    this.bc980005 = value;
                }

                /**
                 * Gets the value of the bc980006 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBc980006() {
                    return bc980006;
                }

                /**
                 * Sets the value of the bc980006 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBc980006(Double value) {
                    this.bc980006 = value;
                }

                /**
                 * Gets the value of the bi360001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBi360001() {
                    return bi360001;
                }

                /**
                 * Sets the value of the bi360001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBi360001(Double value) {
                    this.bi360001 = value;
                }

                /**
                 * Gets the value of the br200001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBr200001() {
                    return br200001;
                }

                /**
                 * Sets the value of the br200001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBr200001(Double value) {
                    this.br200001 = value;
                }

                /**
                 * Gets the value of the br280001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBr280001() {
                    return br280001;
                }

                /**
                 * Sets the value of the br280001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBr280001(Double value) {
                    this.br280001 = value;
                }

                /**
                 * Gets the value of the br280002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBr280002() {
                    return br280002;
                }

                /**
                 * Sets the value of the br280002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBr280002(Double value) {
                    this.br280002 = value;
                }

                /**
                 * Gets the value of the br280003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBr280003() {
                    return br280003;
                }

                /**
                 * Sets the value of the br280003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBr280003(Double value) {
                    this.br280003 = value;
                }

                /**
                 * Gets the value of the br320001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getBr320001() {
                    return br320001;
                }

                /**
                 * Sets the value of the br320001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setBr320001(Double value) {
                    this.br320001 = value;
                }

                /**
                 * Gets the value of the cb300001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb300001() {
                    return cb300001;
                }

                /**
                 * Sets the value of the cb300001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb300001(Double value) {
                    this.cb300001 = value;
                }

                /**
                 * Gets the value of the cb300002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb300002() {
                    return cb300002;
                }

                /**
                 * Sets the value of the cb300002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb300002(Double value) {
                    this.cb300002 = value;
                }

                /**
                 * Gets the value of the cb300003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb300003() {
                    return cb300003;
                }

                /**
                 * Sets the value of the cb300003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb300003(Double value) {
                    this.cb300003 = value;
                }

                /**
                 * Gets the value of the cb300004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb300004() {
                    return cb300004;
                }

                /**
                 * Sets the value of the cb300004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb300004(Double value) {
                    this.cb300004 = value;
                }

                /**
                 * Gets the value of the cb300601 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb300601() {
                    return cb300601;
                }

                /**
                 * Sets the value of the cb300601 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb300601(Double value) {
                    this.cb300601 = value;
                }

                /**
                 * Gets the value of the cb600601 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getCb600601() {
                    return cb600601;
                }

                /**
                 * Sets the value of the cb600601 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setCb600601(Double value) {
                    this.cb600601 = value;
                }

                /**
                 * Gets the value of the ds350001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getDs350001() {
                    return ds350001;
                }

                /**
                 * Sets the value of the ds350001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setDs350001(Double value) {
                    this.ds350001 = value;
                }

                /**
                 * Gets the value of the ds350002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getDs350002() {
                    return ds350002;
                }

                /**
                 * Sets the value of the ds350002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setDs350002(Double value) {
                    this.ds350002 = value;
                }

                /**
                 * Gets the value of the ds350003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getDs350003() {
                    return ds350003;
                }

                /**
                 * Sets the value of the ds350003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setDs350003(Double value) {
                    this.ds350003 = value;
                }

                /**
                 * Gets the value of the ds350004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getDs350004() {
                    return ds350004;
                }

                /**
                 * Sets the value of the ds350004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setDs350004(Double value) {
                    this.ds350004 = value;
                }

                /**
                 * Gets the value of the fi200001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getFi200001() {
                    return fi200001;
                }

                /**
                 * Sets the value of the fi200001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setFi200001(Double value) {
                    this.fi200001 = value;
                }

                /**
                 * Gets the value of the fr280001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getFr280001() {
                    return fr280001;
                }

                /**
                 * Sets the value of the fr280001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setFr280001(Double value) {
                    this.fr280001 = value;
                }

                /**
                 * Gets the value of the fr340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getFr340001() {
                    return fr340001;
                }

                /**
                 * Sets the value of the fr340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setFr340001(Double value) {
                    this.fr340001 = value;
                }

                /**
                 * Gets the value of the g0170001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0170001() {
                    return g0170001;
                }

                /**
                 * Sets the value of the g0170001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0170001(Double value) {
                    this.g0170001 = value;
                }

                /**
                 * Gets the value of the g0430001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0430001() {
                    return g0430001;
                }

                /**
                 * Sets the value of the g0430001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0430001(Double value) {
                    this.g0430001 = value;
                }

                /**
                 * Gets the value of the g043Y001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG043Y001() {
                    return g043Y001;
                }

                /**
                 * Sets the value of the g043Y001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG043Y001(Double value) {
                    this.g043Y001 = value;
                }

                /**
                 * Gets the value of the g0490001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0490001() {
                    return g0490001;
                }

                /**
                 * Sets the value of the g0490001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0490001(Double value) {
                    this.g0490001 = value;
                }

                /**
                 * Gets the value of the g0510001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510001() {
                    return g0510001;
                }

                /**
                 * Sets the value of the g0510001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510001(Double value) {
                    this.g0510001 = value;
                }

                /**
                 * Gets the value of the g0510002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510002() {
                    return g0510002;
                }

                /**
                 * Sets the value of the g0510002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510002(Double value) {
                    this.g0510002 = value;
                }

                /**
                 * Gets the value of the g0510003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510003() {
                    return g0510003;
                }

                /**
                 * Sets the value of the g0510003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510003(Double value) {
                    this.g0510003 = value;
                }

                /**
                 * Gets the value of the g0510004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510004() {
                    return g0510004;
                }

                /**
                 * Sets the value of the g0510004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510004(Double value) {
                    this.g0510004 = value;
                }

                /**
                 * Gets the value of the g0510005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510005() {
                    return g0510005;
                }

                /**
                 * Sets the value of the g0510005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510005(Double value) {
                    this.g0510005 = value;
                }

                /**
                 * Gets the value of the g0510006 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510006() {
                    return g0510006;
                }

                /**
                 * Sets the value of the g0510006 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510006(Double value) {
                    this.g0510006 = value;
                }

                /**
                 * Gets the value of the g0510007 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0510007() {
                    return g0510007;
                }

                /**
                 * Sets the value of the g0510007 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0510007(Double value) {
                    this.g0510007 = value;
                }

                /**
                 * Gets the value of the g0590001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0590001() {
                    return g0590001;
                }

                /**
                 * Sets the value of the g0590001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0590001(Double value) {
                    this.g0590001 = value;
                }

                /**
                 * Gets the value of the g0590002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0590002() {
                    return g0590002;
                }

                /**
                 * Sets the value of the g0590002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0590002(Double value) {
                    this.g0590002 = value;
                }

                /**
                 * Gets the value of the g0630001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0630001() {
                    return g0630001;
                }

                /**
                 * Sets the value of the g0630001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0630001(Double value) {
                    this.g0630001 = value;
                }

                /**
                 * Gets the value of the g0910001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0910001() {
                    return g0910001;
                }

                /**
                 * Sets the value of the g0910001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0910001(Double value) {
                    this.g0910001 = value;
                }

                /**
                 * Gets the value of the g0910002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG0910002() {
                    return g0910002;
                }

                /**
                 * Sets the value of the g0910002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG0910002(Double value) {
                    this.g0910002 = value;
                }

                /**
                 * Gets the value of the g1030001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG1030001() {
                    return g1030001;
                }

                /**
                 * Sets the value of the g1030001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG1030001(Double value) {
                    this.g1030001 = value;
                }

                /**
                 * Gets the value of the g1030002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG1030002() {
                    return g1030002;
                }

                /**
                 * Sets the value of the g1030002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG1030002(Double value) {
                    this.g1030002 = value;
                }

                /**
                 * Gets the value of the g1030003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG1030003() {
                    return g1030003;
                }

                /**
                 * Sets the value of the g1030003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG1030003(Double value) {
                    this.g1030003 = value;
                }

                /**
                 * Gets the value of the g1030004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG1030004() {
                    return g1030004;
                }

                /**
                 * Sets the value of the g1030004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG1030004(Double value) {
                    this.g1030004 = value;
                }

                /**
                 * Gets the value of the g9600001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG9600001() {
                    return g9600001;
                }

                /**
                 * Sets the value of the g9600001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG9600001(Double value) {
                    this.g9600001 = value;
                }

                /**
                 * Gets the value of the g9600002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG9600002() {
                    return g9600002;
                }

                /**
                 * Sets the value of the g9600002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG9600002(Double value) {
                    this.g9600002 = value;
                }

                /**
                 * Gets the value of the g9600003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG9600003() {
                    return g9600003;
                }

                /**
                 * Sets the value of the g9600003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG9600003(Double value) {
                    this.g9600003 = value;
                }

                /**
                 * Gets the value of the g9600004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getG9600004() {
                    return g9600004;
                }

                /**
                 * Sets the value of the g9600004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setG9600004(Double value) {
                    this.g9600004 = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;all>
             *         &lt;element name="ia010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ia030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ib010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ib010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ib010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ib010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ic010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="if010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="if030101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010404" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010405" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ig010406" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="in280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="in310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="in340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="in340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt020001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt200001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt280001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt280002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0010203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="p0040107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pb010001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpbbc1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpbbc2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpbbc3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpf001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpf002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpf003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctpf004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pctre001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf010201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf040101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf040102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf040103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf040104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf040105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="pf300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *       &lt;/all>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {

            })
            public static class Dummy2 {

                protected Double ia010201;
                protected Double ia030101;
                protected Double ib010301;
                protected Double ib010401;
                protected Double ib010402;
                protected Double ib010403;
                protected Double ic010101;
                protected Double if010201;
                protected Double if030101;
                protected Double ig010101;
                protected Double ig010102;
                protected Double ig010103;
                protected Double ig010104;
                protected Double ig010201;
                protected Double ig010202;
                protected Double ig010203;
                protected Double ig010301;
                protected Double ig010401;
                protected Double ig010402;
                protected Double ig010403;
                protected Double ig010404;
                protected Double ig010405;
                protected Double ig010406;
                protected Double in280101;
                protected Double in310001;
                protected Double in340001;
                protected Double in340002;
                protected Double mt020001;
                protected Double mt200001;
                protected Double mt280001;
                protected Double mt280002;
                protected Double mt340001;
                protected Double mt360001;
                protected Double p0010101;
                protected Double p0010102;
                protected Double p0010103;
                protected Double p0010104;
                protected Double p0010105;
                protected Double p0010201;
                protected Double p0010202;
                protected Double p0010203;
                protected Double p0040101;
                protected Double p0040102;
                protected Double p0040103;
                protected Double p0040104;
                protected Double p0040105;
                protected Double p0040106;
                protected Double p0040107;
                protected Double pb010001;
                protected Double pctpbbc1;
                protected Double pctpbbc2;
                protected Double pctpbbc3;
                protected Double pctpf001;
                protected Double pctpf002;
                protected Double pctpf003;
                protected Double pctpf004;
                protected Double pctre001;
                protected Double pf010201;
                protected Double pf040101;
                protected Double pf040102;
                protected Double pf040103;
                protected Double pf040104;
                protected Double pf040105;
                protected Double pf300001;

                /**
                 * Gets the value of the ia010201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIa010201() {
                    return ia010201;
                }

                /**
                 * Sets the value of the ia010201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIa010201(Double value) {
                    this.ia010201 = value;
                }

                /**
                 * Gets the value of the ia030101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIa030101() {
                    return ia030101;
                }

                /**
                 * Sets the value of the ia030101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIa030101(Double value) {
                    this.ia030101 = value;
                }

                /**
                 * Gets the value of the ib010301 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIb010301() {
                    return ib010301;
                }

                /**
                 * Sets the value of the ib010301 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIb010301(Double value) {
                    this.ib010301 = value;
                }

                /**
                 * Gets the value of the ib010401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIb010401() {
                    return ib010401;
                }

                /**
                 * Sets the value of the ib010401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIb010401(Double value) {
                    this.ib010401 = value;
                }

                /**
                 * Gets the value of the ib010402 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIb010402() {
                    return ib010402;
                }

                /**
                 * Sets the value of the ib010402 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIb010402(Double value) {
                    this.ib010402 = value;
                }

                /**
                 * Gets the value of the ib010403 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIb010403() {
                    return ib010403;
                }

                /**
                 * Sets the value of the ib010403 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIb010403(Double value) {
                    this.ib010403 = value;
                }

                /**
                 * Gets the value of the ic010101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIc010101() {
                    return ic010101;
                }

                /**
                 * Sets the value of the ic010101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIc010101(Double value) {
                    this.ic010101 = value;
                }

                /**
                 * Gets the value of the if010201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIf010201() {
                    return if010201;
                }

                /**
                 * Sets the value of the if010201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIf010201(Double value) {
                    this.if010201 = value;
                }

                /**
                 * Gets the value of the if030101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIf030101() {
                    return if030101;
                }

                /**
                 * Sets the value of the if030101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIf030101(Double value) {
                    this.if030101 = value;
                }

                /**
                 * Gets the value of the ig010101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010101() {
                    return ig010101;
                }

                /**
                 * Sets the value of the ig010101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010101(Double value) {
                    this.ig010101 = value;
                }

                /**
                 * Gets the value of the ig010102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010102() {
                    return ig010102;
                }

                /**
                 * Sets the value of the ig010102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010102(Double value) {
                    this.ig010102 = value;
                }

                /**
                 * Gets the value of the ig010103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010103() {
                    return ig010103;
                }

                /**
                 * Sets the value of the ig010103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010103(Double value) {
                    this.ig010103 = value;
                }

                /**
                 * Gets the value of the ig010104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010104() {
                    return ig010104;
                }

                /**
                 * Sets the value of the ig010104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010104(Double value) {
                    this.ig010104 = value;
                }

                /**
                 * Gets the value of the ig010201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010201() {
                    return ig010201;
                }

                /**
                 * Sets the value of the ig010201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010201(Double value) {
                    this.ig010201 = value;
                }

                /**
                 * Gets the value of the ig010202 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010202() {
                    return ig010202;
                }

                /**
                 * Sets the value of the ig010202 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010202(Double value) {
                    this.ig010202 = value;
                }

                /**
                 * Gets the value of the ig010203 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010203() {
                    return ig010203;
                }

                /**
                 * Sets the value of the ig010203 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010203(Double value) {
                    this.ig010203 = value;
                }

                /**
                 * Gets the value of the ig010301 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010301() {
                    return ig010301;
                }

                /**
                 * Sets the value of the ig010301 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010301(Double value) {
                    this.ig010301 = value;
                }

                /**
                 * Gets the value of the ig010401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010401() {
                    return ig010401;
                }

                /**
                 * Sets the value of the ig010401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010401(Double value) {
                    this.ig010401 = value;
                }

                /**
                 * Gets the value of the ig010402 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010402() {
                    return ig010402;
                }

                /**
                 * Sets the value of the ig010402 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010402(Double value) {
                    this.ig010402 = value;
                }

                /**
                 * Gets the value of the ig010403 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010403() {
                    return ig010403;
                }

                /**
                 * Sets the value of the ig010403 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010403(Double value) {
                    this.ig010403 = value;
                }

                /**
                 * Gets the value of the ig010404 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010404() {
                    return ig010404;
                }

                /**
                 * Sets the value of the ig010404 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010404(Double value) {
                    this.ig010404 = value;
                }

                /**
                 * Gets the value of the ig010405 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010405() {
                    return ig010405;
                }

                /**
                 * Sets the value of the ig010405 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010405(Double value) {
                    this.ig010405 = value;
                }

                /**
                 * Gets the value of the ig010406 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIg010406() {
                    return ig010406;
                }

                /**
                 * Sets the value of the ig010406 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIg010406(Double value) {
                    this.ig010406 = value;
                }

                /**
                 * Gets the value of the in280101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIn280101() {
                    return in280101;
                }

                /**
                 * Sets the value of the in280101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIn280101(Double value) {
                    this.in280101 = value;
                }

                /**
                 * Gets the value of the in310001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIn310001() {
                    return in310001;
                }

                /**
                 * Sets the value of the in310001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIn310001(Double value) {
                    this.in310001 = value;
                }

                /**
                 * Gets the value of the in340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIn340001() {
                    return in340001;
                }

                /**
                 * Sets the value of the in340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIn340001(Double value) {
                    this.in340001 = value;
                }

                /**
                 * Gets the value of the in340002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIn340002() {
                    return in340002;
                }

                /**
                 * Sets the value of the in340002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIn340002(Double value) {
                    this.in340002 = value;
                }

                /**
                 * Gets the value of the mt020001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt020001() {
                    return mt020001;
                }

                /**
                 * Sets the value of the mt020001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt020001(Double value) {
                    this.mt020001 = value;
                }

                /**
                 * Gets the value of the mt200001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt200001() {
                    return mt200001;
                }

                /**
                 * Sets the value of the mt200001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt200001(Double value) {
                    this.mt200001 = value;
                }

                /**
                 * Gets the value of the mt280001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt280001() {
                    return mt280001;
                }

                /**
                 * Sets the value of the mt280001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt280001(Double value) {
                    this.mt280001 = value;
                }

                /**
                 * Gets the value of the mt280002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt280002() {
                    return mt280002;
                }

                /**
                 * Sets the value of the mt280002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt280002(Double value) {
                    this.mt280002 = value;
                }

                /**
                 * Gets the value of the mt340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt340001() {
                    return mt340001;
                }

                /**
                 * Sets the value of the mt340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt340001(Double value) {
                    this.mt340001 = value;
                }

                /**
                 * Gets the value of the mt360001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt360001() {
                    return mt360001;
                }

                /**
                 * Sets the value of the mt360001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt360001(Double value) {
                    this.mt360001 = value;
                }

                /**
                 * Gets the value of the p0010101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010101() {
                    return p0010101;
                }

                /**
                 * Sets the value of the p0010101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010101(Double value) {
                    this.p0010101 = value;
                }

                /**
                 * Gets the value of the p0010102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010102() {
                    return p0010102;
                }

                /**
                 * Sets the value of the p0010102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010102(Double value) {
                    this.p0010102 = value;
                }

                /**
                 * Gets the value of the p0010103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010103() {
                    return p0010103;
                }

                /**
                 * Sets the value of the p0010103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010103(Double value) {
                    this.p0010103 = value;
                }

                /**
                 * Gets the value of the p0010104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010104() {
                    return p0010104;
                }

                /**
                 * Sets the value of the p0010104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010104(Double value) {
                    this.p0010104 = value;
                }

                /**
                 * Gets the value of the p0010105 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010105() {
                    return p0010105;
                }

                /**
                 * Sets the value of the p0010105 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010105(Double value) {
                    this.p0010105 = value;
                }

                /**
                 * Gets the value of the p0010201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010201() {
                    return p0010201;
                }

                /**
                 * Sets the value of the p0010201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010201(Double value) {
                    this.p0010201 = value;
                }

                /**
                 * Gets the value of the p0010202 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010202() {
                    return p0010202;
                }

                /**
                 * Sets the value of the p0010202 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010202(Double value) {
                    this.p0010202 = value;
                }

                /**
                 * Gets the value of the p0010203 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0010203() {
                    return p0010203;
                }

                /**
                 * Sets the value of the p0010203 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0010203(Double value) {
                    this.p0010203 = value;
                }

                /**
                 * Gets the value of the p0040101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040101() {
                    return p0040101;
                }

                /**
                 * Sets the value of the p0040101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040101(Double value) {
                    this.p0040101 = value;
                }

                /**
                 * Gets the value of the p0040102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040102() {
                    return p0040102;
                }

                /**
                 * Sets the value of the p0040102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040102(Double value) {
                    this.p0040102 = value;
                }

                /**
                 * Gets the value of the p0040103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040103() {
                    return p0040103;
                }

                /**
                 * Sets the value of the p0040103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040103(Double value) {
                    this.p0040103 = value;
                }

                /**
                 * Gets the value of the p0040104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040104() {
                    return p0040104;
                }

                /**
                 * Sets the value of the p0040104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040104(Double value) {
                    this.p0040104 = value;
                }

                /**
                 * Gets the value of the p0040105 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040105() {
                    return p0040105;
                }

                /**
                 * Sets the value of the p0040105 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040105(Double value) {
                    this.p0040105 = value;
                }

                /**
                 * Gets the value of the p0040106 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040106() {
                    return p0040106;
                }

                /**
                 * Sets the value of the p0040106 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040106(Double value) {
                    this.p0040106 = value;
                }

                /**
                 * Gets the value of the p0040107 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getP0040107() {
                    return p0040107;
                }

                /**
                 * Sets the value of the p0040107 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setP0040107(Double value) {
                    this.p0040107 = value;
                }

                /**
                 * Gets the value of the pb010001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPb010001() {
                    return pb010001;
                }

                /**
                 * Sets the value of the pb010001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPb010001(Double value) {
                    this.pb010001 = value;
                }

                /**
                 * Gets the value of the pctpbbc1 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpbbc1() {
                    return pctpbbc1;
                }

                /**
                 * Sets the value of the pctpbbc1 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpbbc1(Double value) {
                    this.pctpbbc1 = value;
                }

                /**
                 * Gets the value of the pctpbbc2 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpbbc2() {
                    return pctpbbc2;
                }

                /**
                 * Sets the value of the pctpbbc2 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpbbc2(Double value) {
                    this.pctpbbc2 = value;
                }

                /**
                 * Gets the value of the pctpbbc3 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpbbc3() {
                    return pctpbbc3;
                }

                /**
                 * Sets the value of the pctpbbc3 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpbbc3(Double value) {
                    this.pctpbbc3 = value;
                }

                /**
                 * Gets the value of the pctpf001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpf001() {
                    return pctpf001;
                }

                /**
                 * Sets the value of the pctpf001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpf001(Double value) {
                    this.pctpf001 = value;
                }

                /**
                 * Gets the value of the pctpf002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpf002() {
                    return pctpf002;
                }

                /**
                 * Sets the value of the pctpf002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpf002(Double value) {
                    this.pctpf002 = value;
                }

                /**
                 * Gets the value of the pctpf003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpf003() {
                    return pctpf003;
                }

                /**
                 * Sets the value of the pctpf003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpf003(Double value) {
                    this.pctpf003 = value;
                }

                /**
                 * Gets the value of the pctpf004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctpf004() {
                    return pctpf004;
                }

                /**
                 * Sets the value of the pctpf004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctpf004(Double value) {
                    this.pctpf004 = value;
                }

                /**
                 * Gets the value of the pctre001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPctre001() {
                    return pctre001;
                }

                /**
                 * Sets the value of the pctre001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPctre001(Double value) {
                    this.pctre001 = value;
                }

                /**
                 * Gets the value of the pf010201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf010201() {
                    return pf010201;
                }

                /**
                 * Sets the value of the pf010201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf010201(Double value) {
                    this.pf010201 = value;
                }

                /**
                 * Gets the value of the pf040101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf040101() {
                    return pf040101;
                }

                /**
                 * Sets the value of the pf040101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf040101(Double value) {
                    this.pf040101 = value;
                }

                /**
                 * Gets the value of the pf040102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf040102() {
                    return pf040102;
                }

                /**
                 * Sets the value of the pf040102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf040102(Double value) {
                    this.pf040102 = value;
                }

                /**
                 * Gets the value of the pf040103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf040103() {
                    return pf040103;
                }

                /**
                 * Sets the value of the pf040103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf040103(Double value) {
                    this.pf040103 = value;
                }

                /**
                 * Gets the value of the pf040104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf040104() {
                    return pf040104;
                }

                /**
                 * Sets the value of the pf040104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf040104(Double value) {
                    this.pf040104 = value;
                }

                /**
                 * Gets the value of the pf040105 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf040105() {
                    return pf040105;
                }

                /**
                 * Sets the value of the pf040105 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf040105(Double value) {
                    this.pf040105 = value;
                }

                /**
                 * Gets the value of the pf300001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPf300001() {
                    return pf300001;
                }

                /**
                 * Sets the value of the pf300001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPf300001(Double value) {
                    this.pf300001 = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;all>
             *         &lt;element name="ps011001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps022001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps022002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps036001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps037001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps037002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps037003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps037004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps044001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps044002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps044003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps047001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps047002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps047003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps047004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps048001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps048002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps048003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps048004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps077001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps077002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps077003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps087001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps088001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps088002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps098001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps102001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps107001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps107002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps107003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps111001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps112001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps120001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps120002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps120003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps120004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps134001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps139001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps139002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps139003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps141001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps141002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps141003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps141004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps144001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps147001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps147002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps147003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps148001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps148002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps148003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps148004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps148005" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps262001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps262002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps264001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps339001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps339002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re030001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re280101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re280102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re280103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re280104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re280105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re291201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re291202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re291203" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re300001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re300002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re300003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re340002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re340003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="rt310001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="rt310002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0040001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0040002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0040003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0190001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0190002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0430001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0430002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0430003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0590001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0590002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0640001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0640002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0640003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0680001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0680002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0710001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0710002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *       &lt;/all>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {

            })
            public static class Dummy3 {

                protected Double ps011001;
                protected Double ps022001;
                protected Double ps022002;
                protected Double ps030001;
                protected Double ps036001;
                protected Double ps037001;
                protected Double ps037002;
                protected Double ps037003;
                protected Double ps037004;
                protected Double ps044001;
                protected Double ps044002;
                protected Double ps044003;
                protected Double ps047001;
                protected Double ps047002;
                protected Double ps047003;
                protected Double ps047004;
                protected Double ps048001;
                protected Double ps048002;
                protected Double ps048003;
                protected Double ps048004;
                protected Double ps077001;
                protected Double ps077002;
                protected Double ps077003;
                protected Double ps087001;
                protected Double ps088001;
                protected Double ps088002;
                protected Double ps098001;
                protected Double ps102001;
                protected Double ps107001;
                protected Double ps107002;
                protected Double ps107003;
                protected Double ps110001;
                protected Double ps111001;
                protected Double ps112001;
                protected Double ps120001;
                protected Double ps120002;
                protected Double ps120003;
                protected Double ps120004;
                protected Double ps134001;
                protected Double ps139001;
                protected Double ps139002;
                protected Double ps139003;
                protected Double ps141001;
                protected Double ps141002;
                protected Double ps141003;
                protected Double ps141004;
                protected Double ps144001;
                protected Double ps147001;
                protected Double ps147002;
                protected Double ps147003;
                protected Double ps148001;
                protected Double ps148002;
                protected Double ps148003;
                protected Double ps148004;
                protected Double ps148005;
                protected Double ps262001;
                protected Double ps262002;
                protected Double ps264001;
                protected Double ps339001;
                protected Double ps339002;
                protected Double re030001;
                protected Double re280101;
                protected Double re280102;
                protected Double re280103;
                protected Double re280104;
                protected Double re280105;
                protected Double re291201;
                protected Double re291202;
                protected Double re291203;
                protected Double re300001;
                protected Double re300002;
                protected Double re300003;
                protected Double re340001;
                protected Double re340002;
                protected Double re340003;
                protected Double rt310001;
                protected Double rt310002;
                protected Double s0040001;
                protected Double s0040002;
                protected Double s0040003;
                protected Double s0190001;
                protected Double s0190002;
                protected Double s0430001;
                protected Double s0430002;
                protected Double s0430003;
                protected Double s0590001;
                protected Double s0590002;
                protected Double s0640001;
                protected Double s0640002;
                protected Double s0640003;
                protected Double s0680001;
                protected Double s0680002;
                protected Double s0710001;
                protected Double s0710002;

                /**
                 * Gets the value of the ps011001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs011001() {
                    return ps011001;
                }

                /**
                 * Sets the value of the ps011001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs011001(Double value) {
                    this.ps011001 = value;
                }

                /**
                 * Gets the value of the ps022001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs022001() {
                    return ps022001;
                }

                /**
                 * Sets the value of the ps022001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs022001(Double value) {
                    this.ps022001 = value;
                }

                /**
                 * Gets the value of the ps022002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs022002() {
                    return ps022002;
                }

                /**
                 * Sets the value of the ps022002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs022002(Double value) {
                    this.ps022002 = value;
                }

                /**
                 * Gets the value of the ps030001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs030001() {
                    return ps030001;
                }

                /**
                 * Sets the value of the ps030001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs030001(Double value) {
                    this.ps030001 = value;
                }

                /**
                 * Gets the value of the ps036001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs036001() {
                    return ps036001;
                }

                /**
                 * Sets the value of the ps036001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs036001(Double value) {
                    this.ps036001 = value;
                }

                /**
                 * Gets the value of the ps037001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs037001() {
                    return ps037001;
                }

                /**
                 * Sets the value of the ps037001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs037001(Double value) {
                    this.ps037001 = value;
                }

                /**
                 * Gets the value of the ps037002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs037002() {
                    return ps037002;
                }

                /**
                 * Sets the value of the ps037002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs037002(Double value) {
                    this.ps037002 = value;
                }

                /**
                 * Gets the value of the ps037003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs037003() {
                    return ps037003;
                }

                /**
                 * Sets the value of the ps037003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs037003(Double value) {
                    this.ps037003 = value;
                }

                /**
                 * Gets the value of the ps037004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs037004() {
                    return ps037004;
                }

                /**
                 * Sets the value of the ps037004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs037004(Double value) {
                    this.ps037004 = value;
                }

                /**
                 * Gets the value of the ps044001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs044001() {
                    return ps044001;
                }

                /**
                 * Sets the value of the ps044001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs044001(Double value) {
                    this.ps044001 = value;
                }

                /**
                 * Gets the value of the ps044002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs044002() {
                    return ps044002;
                }

                /**
                 * Sets the value of the ps044002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs044002(Double value) {
                    this.ps044002 = value;
                }

                /**
                 * Gets the value of the ps044003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs044003() {
                    return ps044003;
                }

                /**
                 * Sets the value of the ps044003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs044003(Double value) {
                    this.ps044003 = value;
                }

                /**
                 * Gets the value of the ps047001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs047001() {
                    return ps047001;
                }

                /**
                 * Sets the value of the ps047001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs047001(Double value) {
                    this.ps047001 = value;
                }

                /**
                 * Gets the value of the ps047002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs047002() {
                    return ps047002;
                }

                /**
                 * Sets the value of the ps047002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs047002(Double value) {
                    this.ps047002 = value;
                }

                /**
                 * Gets the value of the ps047003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs047003() {
                    return ps047003;
                }

                /**
                 * Sets the value of the ps047003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs047003(Double value) {
                    this.ps047003 = value;
                }

                /**
                 * Gets the value of the ps047004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs047004() {
                    return ps047004;
                }

                /**
                 * Sets the value of the ps047004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs047004(Double value) {
                    this.ps047004 = value;
                }

                /**
                 * Gets the value of the ps048001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs048001() {
                    return ps048001;
                }

                /**
                 * Sets the value of the ps048001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs048001(Double value) {
                    this.ps048001 = value;
                }

                /**
                 * Gets the value of the ps048002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs048002() {
                    return ps048002;
                }

                /**
                 * Sets the value of the ps048002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs048002(Double value) {
                    this.ps048002 = value;
                }

                /**
                 * Gets the value of the ps048003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs048003() {
                    return ps048003;
                }

                /**
                 * Sets the value of the ps048003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs048003(Double value) {
                    this.ps048003 = value;
                }

                /**
                 * Gets the value of the ps048004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs048004() {
                    return ps048004;
                }

                /**
                 * Sets the value of the ps048004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs048004(Double value) {
                    this.ps048004 = value;
                }

                /**
                 * Gets the value of the ps077001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs077001() {
                    return ps077001;
                }

                /**
                 * Sets the value of the ps077001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs077001(Double value) {
                    this.ps077001 = value;
                }

                /**
                 * Gets the value of the ps077002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs077002() {
                    return ps077002;
                }

                /**
                 * Sets the value of the ps077002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs077002(Double value) {
                    this.ps077002 = value;
                }

                /**
                 * Gets the value of the ps077003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs077003() {
                    return ps077003;
                }

                /**
                 * Sets the value of the ps077003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs077003(Double value) {
                    this.ps077003 = value;
                }

                /**
                 * Gets the value of the ps087001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs087001() {
                    return ps087001;
                }

                /**
                 * Sets the value of the ps087001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs087001(Double value) {
                    this.ps087001 = value;
                }

                /**
                 * Gets the value of the ps088001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs088001() {
                    return ps088001;
                }

                /**
                 * Sets the value of the ps088001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs088001(Double value) {
                    this.ps088001 = value;
                }

                /**
                 * Gets the value of the ps088002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs088002() {
                    return ps088002;
                }

                /**
                 * Sets the value of the ps088002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs088002(Double value) {
                    this.ps088002 = value;
                }

                /**
                 * Gets the value of the ps098001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs098001() {
                    return ps098001;
                }

                /**
                 * Sets the value of the ps098001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs098001(Double value) {
                    this.ps098001 = value;
                }

                /**
                 * Gets the value of the ps102001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs102001() {
                    return ps102001;
                }

                /**
                 * Sets the value of the ps102001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs102001(Double value) {
                    this.ps102001 = value;
                }

                /**
                 * Gets the value of the ps107001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs107001() {
                    return ps107001;
                }

                /**
                 * Sets the value of the ps107001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs107001(Double value) {
                    this.ps107001 = value;
                }

                /**
                 * Gets the value of the ps107002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs107002() {
                    return ps107002;
                }

                /**
                 * Sets the value of the ps107002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs107002(Double value) {
                    this.ps107002 = value;
                }

                /**
                 * Gets the value of the ps107003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs107003() {
                    return ps107003;
                }

                /**
                 * Sets the value of the ps107003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs107003(Double value) {
                    this.ps107003 = value;
                }

                /**
                 * Gets the value of the ps110001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs110001() {
                    return ps110001;
                }

                /**
                 * Sets the value of the ps110001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs110001(Double value) {
                    this.ps110001 = value;
                }

                /**
                 * Gets the value of the ps111001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs111001() {
                    return ps111001;
                }

                /**
                 * Sets the value of the ps111001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs111001(Double value) {
                    this.ps111001 = value;
                }

                /**
                 * Gets the value of the ps112001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs112001() {
                    return ps112001;
                }

                /**
                 * Sets the value of the ps112001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs112001(Double value) {
                    this.ps112001 = value;
                }

                /**
                 * Gets the value of the ps120001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs120001() {
                    return ps120001;
                }

                /**
                 * Sets the value of the ps120001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs120001(Double value) {
                    this.ps120001 = value;
                }

                /**
                 * Gets the value of the ps120002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs120002() {
                    return ps120002;
                }

                /**
                 * Sets the value of the ps120002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs120002(Double value) {
                    this.ps120002 = value;
                }

                /**
                 * Gets the value of the ps120003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs120003() {
                    return ps120003;
                }

                /**
                 * Sets the value of the ps120003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs120003(Double value) {
                    this.ps120003 = value;
                }

                /**
                 * Gets the value of the ps120004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs120004() {
                    return ps120004;
                }

                /**
                 * Sets the value of the ps120004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs120004(Double value) {
                    this.ps120004 = value;
                }

                /**
                 * Gets the value of the ps134001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs134001() {
                    return ps134001;
                }

                /**
                 * Sets the value of the ps134001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs134001(Double value) {
                    this.ps134001 = value;
                }

                /**
                 * Gets the value of the ps139001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs139001() {
                    return ps139001;
                }

                /**
                 * Sets the value of the ps139001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs139001(Double value) {
                    this.ps139001 = value;
                }

                /**
                 * Gets the value of the ps139002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs139002() {
                    return ps139002;
                }

                /**
                 * Sets the value of the ps139002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs139002(Double value) {
                    this.ps139002 = value;
                }

                /**
                 * Gets the value of the ps139003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs139003() {
                    return ps139003;
                }

                /**
                 * Sets the value of the ps139003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs139003(Double value) {
                    this.ps139003 = value;
                }

                /**
                 * Gets the value of the ps141001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs141001() {
                    return ps141001;
                }

                /**
                 * Sets the value of the ps141001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs141001(Double value) {
                    this.ps141001 = value;
                }

                /**
                 * Gets the value of the ps141002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs141002() {
                    return ps141002;
                }

                /**
                 * Sets the value of the ps141002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs141002(Double value) {
                    this.ps141002 = value;
                }

                /**
                 * Gets the value of the ps141003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs141003() {
                    return ps141003;
                }

                /**
                 * Sets the value of the ps141003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs141003(Double value) {
                    this.ps141003 = value;
                }

                /**
                 * Gets the value of the ps141004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs141004() {
                    return ps141004;
                }

                /**
                 * Sets the value of the ps141004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs141004(Double value) {
                    this.ps141004 = value;
                }

                /**
                 * Gets the value of the ps144001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs144001() {
                    return ps144001;
                }

                /**
                 * Sets the value of the ps144001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs144001(Double value) {
                    this.ps144001 = value;
                }

                /**
                 * Gets the value of the ps147001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs147001() {
                    return ps147001;
                }

                /**
                 * Sets the value of the ps147001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs147001(Double value) {
                    this.ps147001 = value;
                }

                /**
                 * Gets the value of the ps147002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs147002() {
                    return ps147002;
                }

                /**
                 * Sets the value of the ps147002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs147002(Double value) {
                    this.ps147002 = value;
                }

                /**
                 * Gets the value of the ps147003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs147003() {
                    return ps147003;
                }

                /**
                 * Sets the value of the ps147003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs147003(Double value) {
                    this.ps147003 = value;
                }

                /**
                 * Gets the value of the ps148001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs148001() {
                    return ps148001;
                }

                /**
                 * Sets the value of the ps148001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs148001(Double value) {
                    this.ps148001 = value;
                }

                /**
                 * Gets the value of the ps148002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs148002() {
                    return ps148002;
                }

                /**
                 * Sets the value of the ps148002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs148002(Double value) {
                    this.ps148002 = value;
                }

                /**
                 * Gets the value of the ps148003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs148003() {
                    return ps148003;
                }

                /**
                 * Sets the value of the ps148003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs148003(Double value) {
                    this.ps148003 = value;
                }

                /**
                 * Gets the value of the ps148004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs148004() {
                    return ps148004;
                }

                /**
                 * Sets the value of the ps148004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs148004(Double value) {
                    this.ps148004 = value;
                }

                /**
                 * Gets the value of the ps148005 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs148005() {
                    return ps148005;
                }

                /**
                 * Sets the value of the ps148005 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs148005(Double value) {
                    this.ps148005 = value;
                }

                /**
                 * Gets the value of the ps262001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs262001() {
                    return ps262001;
                }

                /**
                 * Sets the value of the ps262001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs262001(Double value) {
                    this.ps262001 = value;
                }

                /**
                 * Gets the value of the ps262002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs262002() {
                    return ps262002;
                }

                /**
                 * Sets the value of the ps262002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs262002(Double value) {
                    this.ps262002 = value;
                }

                /**
                 * Gets the value of the ps264001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs264001() {
                    return ps264001;
                }

                /**
                 * Sets the value of the ps264001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs264001(Double value) {
                    this.ps264001 = value;
                }

                /**
                 * Gets the value of the ps339001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs339001() {
                    return ps339001;
                }

                /**
                 * Sets the value of the ps339001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs339001(Double value) {
                    this.ps339001 = value;
                }

                /**
                 * Gets the value of the ps339002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs339002() {
                    return ps339002;
                }

                /**
                 * Sets the value of the ps339002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs339002(Double value) {
                    this.ps339002 = value;
                }

                /**
                 * Gets the value of the re030001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe030001() {
                    return re030001;
                }

                /**
                 * Sets the value of the re030001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe030001(Double value) {
                    this.re030001 = value;
                }

                /**
                 * Gets the value of the re280101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe280101() {
                    return re280101;
                }

                /**
                 * Sets the value of the re280101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe280101(Double value) {
                    this.re280101 = value;
                }

                /**
                 * Gets the value of the re280102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe280102() {
                    return re280102;
                }

                /**
                 * Sets the value of the re280102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe280102(Double value) {
                    this.re280102 = value;
                }

                /**
                 * Gets the value of the re280103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe280103() {
                    return re280103;
                }

                /**
                 * Sets the value of the re280103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe280103(Double value) {
                    this.re280103 = value;
                }

                /**
                 * Gets the value of the re280104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe280104() {
                    return re280104;
                }

                /**
                 * Sets the value of the re280104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe280104(Double value) {
                    this.re280104 = value;
                }

                /**
                 * Gets the value of the re280105 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe280105() {
                    return re280105;
                }

                /**
                 * Sets the value of the re280105 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe280105(Double value) {
                    this.re280105 = value;
                }

                /**
                 * Gets the value of the re291201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe291201() {
                    return re291201;
                }

                /**
                 * Sets the value of the re291201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe291201(Double value) {
                    this.re291201 = value;
                }

                /**
                 * Gets the value of the re291202 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe291202() {
                    return re291202;
                }

                /**
                 * Sets the value of the re291202 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe291202(Double value) {
                    this.re291202 = value;
                }

                /**
                 * Gets the value of the re291203 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe291203() {
                    return re291203;
                }

                /**
                 * Sets the value of the re291203 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe291203(Double value) {
                    this.re291203 = value;
                }

                /**
                 * Gets the value of the re300001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe300001() {
                    return re300001;
                }

                /**
                 * Sets the value of the re300001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe300001(Double value) {
                    this.re300001 = value;
                }

                /**
                 * Gets the value of the re300002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe300002() {
                    return re300002;
                }

                /**
                 * Sets the value of the re300002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe300002(Double value) {
                    this.re300002 = value;
                }

                /**
                 * Gets the value of the re300003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe300003() {
                    return re300003;
                }

                /**
                 * Sets the value of the re300003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe300003(Double value) {
                    this.re300003 = value;
                }

                /**
                 * Gets the value of the re340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe340001() {
                    return re340001;
                }

                /**
                 * Sets the value of the re340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe340001(Double value) {
                    this.re340001 = value;
                }

                /**
                 * Gets the value of the re340002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe340002() {
                    return re340002;
                }

                /**
                 * Sets the value of the re340002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe340002(Double value) {
                    this.re340002 = value;
                }

                /**
                 * Gets the value of the re340003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe340003() {
                    return re340003;
                }

                /**
                 * Sets the value of the re340003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe340003(Double value) {
                    this.re340003 = value;
                }

                /**
                 * Gets the value of the rt310001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRt310001() {
                    return rt310001;
                }

                /**
                 * Sets the value of the rt310001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRt310001(Double value) {
                    this.rt310001 = value;
                }

                /**
                 * Gets the value of the rt310002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRt310002() {
                    return rt310002;
                }

                /**
                 * Sets the value of the rt310002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRt310002(Double value) {
                    this.rt310002 = value;
                }

                /**
                 * Gets the value of the s0040001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0040001() {
                    return s0040001;
                }

                /**
                 * Sets the value of the s0040001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0040001(Double value) {
                    this.s0040001 = value;
                }

                /**
                 * Gets the value of the s0040002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0040002() {
                    return s0040002;
                }

                /**
                 * Sets the value of the s0040002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0040002(Double value) {
                    this.s0040002 = value;
                }

                /**
                 * Gets the value of the s0040003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0040003() {
                    return s0040003;
                }

                /**
                 * Sets the value of the s0040003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0040003(Double value) {
                    this.s0040003 = value;
                }

                /**
                 * Gets the value of the s0190001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0190001() {
                    return s0190001;
                }

                /**
                 * Sets the value of the s0190001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0190001(Double value) {
                    this.s0190001 = value;
                }

                /**
                 * Gets the value of the s0190002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0190002() {
                    return s0190002;
                }

                /**
                 * Sets the value of the s0190002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0190002(Double value) {
                    this.s0190002 = value;
                }

                /**
                 * Gets the value of the s0430001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0430001() {
                    return s0430001;
                }

                /**
                 * Sets the value of the s0430001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0430001(Double value) {
                    this.s0430001 = value;
                }

                /**
                 * Gets the value of the s0430002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0430002() {
                    return s0430002;
                }

                /**
                 * Sets the value of the s0430002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0430002(Double value) {
                    this.s0430002 = value;
                }

                /**
                 * Gets the value of the s0430003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0430003() {
                    return s0430003;
                }

                /**
                 * Sets the value of the s0430003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0430003(Double value) {
                    this.s0430003 = value;
                }

                /**
                 * Gets the value of the s0590001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0590001() {
                    return s0590001;
                }

                /**
                 * Sets the value of the s0590001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0590001(Double value) {
                    this.s0590001 = value;
                }

                /**
                 * Gets the value of the s0590002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0590002() {
                    return s0590002;
                }

                /**
                 * Sets the value of the s0590002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0590002(Double value) {
                    this.s0590002 = value;
                }

                /**
                 * Gets the value of the s0640001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0640001() {
                    return s0640001;
                }

                /**
                 * Sets the value of the s0640001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0640001(Double value) {
                    this.s0640001 = value;
                }

                /**
                 * Gets the value of the s0640002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0640002() {
                    return s0640002;
                }

                /**
                 * Sets the value of the s0640002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0640002(Double value) {
                    this.s0640002 = value;
                }

                /**
                 * Gets the value of the s0640003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0640003() {
                    return s0640003;
                }

                /**
                 * Sets the value of the s0640003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0640003(Double value) {
                    this.s0640003 = value;
                }

                /**
                 * Gets the value of the s0680001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0680001() {
                    return s0680001;
                }

                /**
                 * Sets the value of the s0680001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0680001(Double value) {
                    this.s0680001 = value;
                }

                /**
                 * Gets the value of the s0680002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0680002() {
                    return s0680002;
                }

                /**
                 * Sets the value of the s0680002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0680002(Double value) {
                    this.s0680002 = value;
                }

                /**
                 * Gets the value of the s0710001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0710001() {
                    return s0710001;
                }

                /**
                 * Sets the value of the s0710001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0710001(Double value) {
                    this.s0710001 = value;
                }

                /**
                 * Gets the value of the s0710002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0710002() {
                    return s0710002;
                }

                /**
                 * Sets the value of the s0710002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0710002(Double value) {
                    this.s0710002 = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;all>
             *         &lt;element name="t0040301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0040501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0040502" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0040503" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0040902" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t00510001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t00510002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t00510003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0051001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0051002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0051003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0051004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0080202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0110401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0110402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130303" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130304" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0130305" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0202d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0202d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0202d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0202d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0202d05" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0203d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0203d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0203d04" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t340001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290105" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290106" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290107" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290108" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290109" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290110" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290111" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ta290112" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tb300101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf040901" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf190101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf190102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf190103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf203d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf290101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf290102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tg270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tg270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tg270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="th090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ti090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk110501" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk260101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk260102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk260103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk260104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk270102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk270103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk270104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="to300201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tp270101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130301" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130302" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130402" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ts130403" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tv090101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="at110001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="atop1801" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="fi360001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ia010401" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="if010101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="mt570001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="ps131001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="re210001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0590003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0590004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0620001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0620002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0620003" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0620004" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0700001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s0700002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s1150001" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="s1150002" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0201d01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0201d02" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0201d03" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="t0301a01" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf160201" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tf160202" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk080101" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk080102" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk080103" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *         &lt;element name="tk080104" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
             *       &lt;/all>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {

            })
            public static class Dummy4 {

                protected Double t0040301;
                protected Double t0040501;
                protected Double t0040502;
                protected Double t0040503;
                protected Double t0040901;
                protected Double t0040902;
                protected Double t00510001;
                protected Double t00510002;
                protected Double t00510003;
                protected Double t0051001;
                protected Double t0051002;
                protected Double t0051003;
                protected Double t0051004;
                protected Double t0080101;
                protected Double t0080102;
                protected Double t0080103;
                protected Double t0080104;
                protected Double t0080201;
                protected Double t0080202;
                protected Double t0110401;
                protected Double t0110402;
                protected Double t0130101;
                protected Double t0130102;
                protected Double t0130201;
                protected Double t0130301;
                protected Double t0130302;
                protected Double t0130303;
                protected Double t0130304;
                protected Double t0130305;
                @XmlElement(name = "t0202d01")
                protected Double t0202D01;
                @XmlElement(name = "t0202d02")
                protected Double t0202D02;
                @XmlElement(name = "t0202d03")
                protected Double t0202D03;
                @XmlElement(name = "t0202d04")
                protected Double t0202D04;
                @XmlElement(name = "t0202d05")
                protected Double t0202D05;
                @XmlElement(name = "t0203d01")
                protected Double t0203D01;
                @XmlElement(name = "t0203d02")
                protected Double t0203D02;
                @XmlElement(name = "t0203d03")
                protected Double t0203D03;
                @XmlElement(name = "t0203d04")
                protected Double t0203D04;
                protected Double t340001;
                protected Double ta080101;
                protected Double ta290101;
                protected Double ta290102;
                protected Double ta290103;
                protected Double ta290104;
                protected Double ta290105;
                protected Double ta290106;
                protected Double ta290107;
                protected Double ta290108;
                protected Double ta290109;
                protected Double ta290110;
                protected Double ta290111;
                protected Double ta290112;
                protected Double tb300101;
                protected Double tf040901;
                protected Double tf190101;
                protected Double tf190102;
                protected Double tf190103;
                @XmlElement(name = "tf203d01")
                protected Double tf203D01;
                protected Double tf260101;
                protected Double tf270101;
                protected Double tf290101;
                protected Double tf290102;
                protected Double tg270101;
                protected Double tg270102;
                protected Double tg270103;
                protected Double th090101;
                protected Double ti090101;
                protected Double tk090101;
                protected Double tk110501;
                protected Double tk260101;
                protected Double tk260102;
                protected Double tk260103;
                protected Double tk260104;
                protected Double tk270101;
                protected Double tk270102;
                protected Double tk270103;
                protected Double tk270104;
                protected Double to300201;
                protected Double tp270101;
                protected Double ts130101;
                protected Double ts130102;
                protected Double ts130301;
                protected Double ts130302;
                protected Double ts130401;
                protected Double ts130402;
                protected Double ts130403;
                protected Double tv090101;
                protected Double at110001;
                protected Double atop1801;
                protected Double fi360001;
                protected Double ia010401;
                protected Double if010101;
                protected Double mt570001;
                protected Double ps131001;
                protected Double re210001;
                protected Double s0590003;
                protected Double s0590004;
                protected Double s0620001;
                protected Double s0620002;
                protected Double s0620003;
                protected Double s0620004;
                protected Double s0700001;
                protected Double s0700002;
                protected Double s1150001;
                protected Double s1150002;
                @XmlElement(name = "t0201d01")
                protected Double t0201D01;
                @XmlElement(name = "t0201d02")
                protected Double t0201D02;
                @XmlElement(name = "t0201d03")
                protected Double t0201D03;
                @XmlElement(name = "t0301a01")
                protected Double t0301A01;
                protected Double tf160201;
                protected Double tf160202;
                protected Double tk080101;
                protected Double tk080102;
                protected Double tk080103;
                protected Double tk080104;

                /**
                 * Gets the value of the t0040301 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040301() {
                    return t0040301;
                }

                /**
                 * Sets the value of the t0040301 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040301(Double value) {
                    this.t0040301 = value;
                }

                /**
                 * Gets the value of the t0040501 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040501() {
                    return t0040501;
                }

                /**
                 * Sets the value of the t0040501 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040501(Double value) {
                    this.t0040501 = value;
                }

                /**
                 * Gets the value of the t0040502 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040502() {
                    return t0040502;
                }

                /**
                 * Sets the value of the t0040502 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040502(Double value) {
                    this.t0040502 = value;
                }

                /**
                 * Gets the value of the t0040503 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040503() {
                    return t0040503;
                }

                /**
                 * Sets the value of the t0040503 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040503(Double value) {
                    this.t0040503 = value;
                }

                /**
                 * Gets the value of the t0040901 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040901() {
                    return t0040901;
                }

                /**
                 * Sets the value of the t0040901 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040901(Double value) {
                    this.t0040901 = value;
                }

                /**
                 * Gets the value of the t0040902 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0040902() {
                    return t0040902;
                }

                /**
                 * Sets the value of the t0040902 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0040902(Double value) {
                    this.t0040902 = value;
                }

                /**
                 * Gets the value of the t00510001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT00510001() {
                    return t00510001;
                }

                /**
                 * Sets the value of the t00510001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT00510001(Double value) {
                    this.t00510001 = value;
                }

                /**
                 * Gets the value of the t00510002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT00510002() {
                    return t00510002;
                }

                /**
                 * Sets the value of the t00510002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT00510002(Double value) {
                    this.t00510002 = value;
                }

                /**
                 * Gets the value of the t00510003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT00510003() {
                    return t00510003;
                }

                /**
                 * Sets the value of the t00510003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT00510003(Double value) {
                    this.t00510003 = value;
                }

                /**
                 * Gets the value of the t0051001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0051001() {
                    return t0051001;
                }

                /**
                 * Sets the value of the t0051001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0051001(Double value) {
                    this.t0051001 = value;
                }

                /**
                 * Gets the value of the t0051002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0051002() {
                    return t0051002;
                }

                /**
                 * Sets the value of the t0051002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0051002(Double value) {
                    this.t0051002 = value;
                }

                /**
                 * Gets the value of the t0051003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0051003() {
                    return t0051003;
                }

                /**
                 * Sets the value of the t0051003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0051003(Double value) {
                    this.t0051003 = value;
                }

                /**
                 * Gets the value of the t0051004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0051004() {
                    return t0051004;
                }

                /**
                 * Sets the value of the t0051004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0051004(Double value) {
                    this.t0051004 = value;
                }

                /**
                 * Gets the value of the t0080101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080101() {
                    return t0080101;
                }

                /**
                 * Sets the value of the t0080101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080101(Double value) {
                    this.t0080101 = value;
                }

                /**
                 * Gets the value of the t0080102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080102() {
                    return t0080102;
                }

                /**
                 * Sets the value of the t0080102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080102(Double value) {
                    this.t0080102 = value;
                }

                /**
                 * Gets the value of the t0080103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080103() {
                    return t0080103;
                }

                /**
                 * Sets the value of the t0080103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080103(Double value) {
                    this.t0080103 = value;
                }

                /**
                 * Gets the value of the t0080104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080104() {
                    return t0080104;
                }

                /**
                 * Sets the value of the t0080104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080104(Double value) {
                    this.t0080104 = value;
                }

                /**
                 * Gets the value of the t0080201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080201() {
                    return t0080201;
                }

                /**
                 * Sets the value of the t0080201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080201(Double value) {
                    this.t0080201 = value;
                }

                /**
                 * Gets the value of the t0080202 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0080202() {
                    return t0080202;
                }

                /**
                 * Sets the value of the t0080202 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0080202(Double value) {
                    this.t0080202 = value;
                }

                /**
                 * Gets the value of the t0110401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0110401() {
                    return t0110401;
                }

                /**
                 * Sets the value of the t0110401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0110401(Double value) {
                    this.t0110401 = value;
                }

                /**
                 * Gets the value of the t0110402 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0110402() {
                    return t0110402;
                }

                /**
                 * Sets the value of the t0110402 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0110402(Double value) {
                    this.t0110402 = value;
                }

                /**
                 * Gets the value of the t0130101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130101() {
                    return t0130101;
                }

                /**
                 * Sets the value of the t0130101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130101(Double value) {
                    this.t0130101 = value;
                }

                /**
                 * Gets the value of the t0130102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130102() {
                    return t0130102;
                }

                /**
                 * Sets the value of the t0130102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130102(Double value) {
                    this.t0130102 = value;
                }

                /**
                 * Gets the value of the t0130201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130201() {
                    return t0130201;
                }

                /**
                 * Sets the value of the t0130201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130201(Double value) {
                    this.t0130201 = value;
                }

                /**
                 * Gets the value of the t0130301 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130301() {
                    return t0130301;
                }

                /**
                 * Sets the value of the t0130301 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130301(Double value) {
                    this.t0130301 = value;
                }

                /**
                 * Gets the value of the t0130302 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130302() {
                    return t0130302;
                }

                /**
                 * Sets the value of the t0130302 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130302(Double value) {
                    this.t0130302 = value;
                }

                /**
                 * Gets the value of the t0130303 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130303() {
                    return t0130303;
                }

                /**
                 * Sets the value of the t0130303 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130303(Double value) {
                    this.t0130303 = value;
                }

                /**
                 * Gets the value of the t0130304 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130304() {
                    return t0130304;
                }

                /**
                 * Sets the value of the t0130304 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130304(Double value) {
                    this.t0130304 = value;
                }

                /**
                 * Gets the value of the t0130305 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0130305() {
                    return t0130305;
                }

                /**
                 * Sets the value of the t0130305 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0130305(Double value) {
                    this.t0130305 = value;
                }

                /**
                 * Gets the value of the t0202D01 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0202D01() {
                    return t0202D01;
                }

                /**
                 * Sets the value of the t0202D01 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0202D01(Double value) {
                    this.t0202D01 = value;
                }

                /**
                 * Gets the value of the t0202D02 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0202D02() {
                    return t0202D02;
                }

                /**
                 * Sets the value of the t0202D02 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0202D02(Double value) {
                    this.t0202D02 = value;
                }

                /**
                 * Gets the value of the t0202D03 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0202D03() {
                    return t0202D03;
                }

                /**
                 * Sets the value of the t0202D03 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0202D03(Double value) {
                    this.t0202D03 = value;
                }

                /**
                 * Gets the value of the t0202D04 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0202D04() {
                    return t0202D04;
                }

                /**
                 * Sets the value of the t0202D04 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0202D04(Double value) {
                    this.t0202D04 = value;
                }

                /**
                 * Gets the value of the t0202D05 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0202D05() {
                    return t0202D05;
                }

                /**
                 * Sets the value of the t0202D05 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0202D05(Double value) {
                    this.t0202D05 = value;
                }

                /**
                 * Gets the value of the t0203D01 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0203D01() {
                    return t0203D01;
                }

                /**
                 * Sets the value of the t0203D01 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0203D01(Double value) {
                    this.t0203D01 = value;
                }

                /**
                 * Gets the value of the t0203D02 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0203D02() {
                    return t0203D02;
                }

                /**
                 * Sets the value of the t0203D02 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0203D02(Double value) {
                    this.t0203D02 = value;
                }

                /**
                 * Gets the value of the t0203D03 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0203D03() {
                    return t0203D03;
                }

                /**
                 * Sets the value of the t0203D03 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0203D03(Double value) {
                    this.t0203D03 = value;
                }

                /**
                 * Gets the value of the t0203D04 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0203D04() {
                    return t0203D04;
                }

                /**
                 * Sets the value of the t0203D04 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0203D04(Double value) {
                    this.t0203D04 = value;
                }

                /**
                 * Gets the value of the t340001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT340001() {
                    return t340001;
                }

                /**
                 * Sets the value of the t340001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT340001(Double value) {
                    this.t340001 = value;
                }

                /**
                 * Gets the value of the ta080101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa080101() {
                    return ta080101;
                }

                /**
                 * Sets the value of the ta080101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa080101(Double value) {
                    this.ta080101 = value;
                }

                /**
                 * Gets the value of the ta290101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290101() {
                    return ta290101;
                }

                /**
                 * Sets the value of the ta290101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290101(Double value) {
                    this.ta290101 = value;
                }

                /**
                 * Gets the value of the ta290102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290102() {
                    return ta290102;
                }

                /**
                 * Sets the value of the ta290102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290102(Double value) {
                    this.ta290102 = value;
                }

                /**
                 * Gets the value of the ta290103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290103() {
                    return ta290103;
                }

                /**
                 * Sets the value of the ta290103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290103(Double value) {
                    this.ta290103 = value;
                }

                /**
                 * Gets the value of the ta290104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290104() {
                    return ta290104;
                }

                /**
                 * Sets the value of the ta290104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290104(Double value) {
                    this.ta290104 = value;
                }

                /**
                 * Gets the value of the ta290105 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290105() {
                    return ta290105;
                }

                /**
                 * Sets the value of the ta290105 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290105(Double value) {
                    this.ta290105 = value;
                }

                /**
                 * Gets the value of the ta290106 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290106() {
                    return ta290106;
                }

                /**
                 * Sets the value of the ta290106 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290106(Double value) {
                    this.ta290106 = value;
                }

                /**
                 * Gets the value of the ta290107 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290107() {
                    return ta290107;
                }

                /**
                 * Sets the value of the ta290107 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290107(Double value) {
                    this.ta290107 = value;
                }

                /**
                 * Gets the value of the ta290108 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290108() {
                    return ta290108;
                }

                /**
                 * Sets the value of the ta290108 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290108(Double value) {
                    this.ta290108 = value;
                }

                /**
                 * Gets the value of the ta290109 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290109() {
                    return ta290109;
                }

                /**
                 * Sets the value of the ta290109 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290109(Double value) {
                    this.ta290109 = value;
                }

                /**
                 * Gets the value of the ta290110 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290110() {
                    return ta290110;
                }

                /**
                 * Sets the value of the ta290110 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290110(Double value) {
                    this.ta290110 = value;
                }

                /**
                 * Gets the value of the ta290111 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290111() {
                    return ta290111;
                }

                /**
                 * Sets the value of the ta290111 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290111(Double value) {
                    this.ta290111 = value;
                }

                /**
                 * Gets the value of the ta290112 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTa290112() {
                    return ta290112;
                }

                /**
                 * Sets the value of the ta290112 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTa290112(Double value) {
                    this.ta290112 = value;
                }

                /**
                 * Gets the value of the tb300101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTb300101() {
                    return tb300101;
                }

                /**
                 * Sets the value of the tb300101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTb300101(Double value) {
                    this.tb300101 = value;
                }

                /**
                 * Gets the value of the tf040901 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf040901() {
                    return tf040901;
                }

                /**
                 * Sets the value of the tf040901 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf040901(Double value) {
                    this.tf040901 = value;
                }

                /**
                 * Gets the value of the tf190101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf190101() {
                    return tf190101;
                }

                /**
                 * Sets the value of the tf190101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf190101(Double value) {
                    this.tf190101 = value;
                }

                /**
                 * Gets the value of the tf190102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf190102() {
                    return tf190102;
                }

                /**
                 * Sets the value of the tf190102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf190102(Double value) {
                    this.tf190102 = value;
                }

                /**
                 * Gets the value of the tf190103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf190103() {
                    return tf190103;
                }

                /**
                 * Sets the value of the tf190103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf190103(Double value) {
                    this.tf190103 = value;
                }

                /**
                 * Gets the value of the tf203D01 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf203D01() {
                    return tf203D01;
                }

                /**
                 * Sets the value of the tf203D01 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf203D01(Double value) {
                    this.tf203D01 = value;
                }

                /**
                 * Gets the value of the tf260101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf260101() {
                    return tf260101;
                }

                /**
                 * Sets the value of the tf260101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf260101(Double value) {
                    this.tf260101 = value;
                }

                /**
                 * Gets the value of the tf270101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf270101() {
                    return tf270101;
                }

                /**
                 * Sets the value of the tf270101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf270101(Double value) {
                    this.tf270101 = value;
                }

                /**
                 * Gets the value of the tf290101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf290101() {
                    return tf290101;
                }

                /**
                 * Sets the value of the tf290101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf290101(Double value) {
                    this.tf290101 = value;
                }

                /**
                 * Gets the value of the tf290102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf290102() {
                    return tf290102;
                }

                /**
                 * Sets the value of the tf290102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf290102(Double value) {
                    this.tf290102 = value;
                }

                /**
                 * Gets the value of the tg270101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTg270101() {
                    return tg270101;
                }

                /**
                 * Sets the value of the tg270101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTg270101(Double value) {
                    this.tg270101 = value;
                }

                /**
                 * Gets the value of the tg270102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTg270102() {
                    return tg270102;
                }

                /**
                 * Sets the value of the tg270102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTg270102(Double value) {
                    this.tg270102 = value;
                }

                /**
                 * Gets the value of the tg270103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTg270103() {
                    return tg270103;
                }

                /**
                 * Sets the value of the tg270103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTg270103(Double value) {
                    this.tg270103 = value;
                }

                /**
                 * Gets the value of the th090101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTh090101() {
                    return th090101;
                }

                /**
                 * Sets the value of the th090101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTh090101(Double value) {
                    this.th090101 = value;
                }

                /**
                 * Gets the value of the ti090101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTi090101() {
                    return ti090101;
                }

                /**
                 * Sets the value of the ti090101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTi090101(Double value) {
                    this.ti090101 = value;
                }

                /**
                 * Gets the value of the tk090101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk090101() {
                    return tk090101;
                }

                /**
                 * Sets the value of the tk090101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk090101(Double value) {
                    this.tk090101 = value;
                }

                /**
                 * Gets the value of the tk110501 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk110501() {
                    return tk110501;
                }

                /**
                 * Sets the value of the tk110501 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk110501(Double value) {
                    this.tk110501 = value;
                }

                /**
                 * Gets the value of the tk260101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk260101() {
                    return tk260101;
                }

                /**
                 * Sets the value of the tk260101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk260101(Double value) {
                    this.tk260101 = value;
                }

                /**
                 * Gets the value of the tk260102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk260102() {
                    return tk260102;
                }

                /**
                 * Sets the value of the tk260102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk260102(Double value) {
                    this.tk260102 = value;
                }

                /**
                 * Gets the value of the tk260103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk260103() {
                    return tk260103;
                }

                /**
                 * Sets the value of the tk260103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk260103(Double value) {
                    this.tk260103 = value;
                }

                /**
                 * Gets the value of the tk260104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk260104() {
                    return tk260104;
                }

                /**
                 * Sets the value of the tk260104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk260104(Double value) {
                    this.tk260104 = value;
                }

                /**
                 * Gets the value of the tk270101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk270101() {
                    return tk270101;
                }

                /**
                 * Sets the value of the tk270101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk270101(Double value) {
                    this.tk270101 = value;
                }

                /**
                 * Gets the value of the tk270102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk270102() {
                    return tk270102;
                }

                /**
                 * Sets the value of the tk270102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk270102(Double value) {
                    this.tk270102 = value;
                }

                /**
                 * Gets the value of the tk270103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk270103() {
                    return tk270103;
                }

                /**
                 * Sets the value of the tk270103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk270103(Double value) {
                    this.tk270103 = value;
                }

                /**
                 * Gets the value of the tk270104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk270104() {
                    return tk270104;
                }

                /**
                 * Sets the value of the tk270104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk270104(Double value) {
                    this.tk270104 = value;
                }

                /**
                 * Gets the value of the to300201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTo300201() {
                    return to300201;
                }

                /**
                 * Sets the value of the to300201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTo300201(Double value) {
                    this.to300201 = value;
                }

                /**
                 * Gets the value of the tp270101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTp270101() {
                    return tp270101;
                }

                /**
                 * Sets the value of the tp270101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTp270101(Double value) {
                    this.tp270101 = value;
                }

                /**
                 * Gets the value of the ts130101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130101() {
                    return ts130101;
                }

                /**
                 * Sets the value of the ts130101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130101(Double value) {
                    this.ts130101 = value;
                }

                /**
                 * Gets the value of the ts130102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130102() {
                    return ts130102;
                }

                /**
                 * Sets the value of the ts130102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130102(Double value) {
                    this.ts130102 = value;
                }

                /**
                 * Gets the value of the ts130301 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130301() {
                    return ts130301;
                }

                /**
                 * Sets the value of the ts130301 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130301(Double value) {
                    this.ts130301 = value;
                }

                /**
                 * Gets the value of the ts130302 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130302() {
                    return ts130302;
                }

                /**
                 * Sets the value of the ts130302 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130302(Double value) {
                    this.ts130302 = value;
                }

                /**
                 * Gets the value of the ts130401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130401() {
                    return ts130401;
                }

                /**
                 * Sets the value of the ts130401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130401(Double value) {
                    this.ts130401 = value;
                }

                /**
                 * Gets the value of the ts130402 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130402() {
                    return ts130402;
                }

                /**
                 * Sets the value of the ts130402 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130402(Double value) {
                    this.ts130402 = value;
                }

                /**
                 * Gets the value of the ts130403 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTs130403() {
                    return ts130403;
                }

                /**
                 * Sets the value of the ts130403 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTs130403(Double value) {
                    this.ts130403 = value;
                }

                /**
                 * Gets the value of the tv090101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTv090101() {
                    return tv090101;
                }

                /**
                 * Sets the value of the tv090101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTv090101(Double value) {
                    this.tv090101 = value;
                }

                /**
                 * Gets the value of the at110001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAt110001() {
                    return at110001;
                }

                /**
                 * Sets the value of the at110001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAt110001(Double value) {
                    this.at110001 = value;
                }

                /**
                 * Gets the value of the atop1801 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getAtop1801() {
                    return atop1801;
                }

                /**
                 * Sets the value of the atop1801 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setAtop1801(Double value) {
                    this.atop1801 = value;
                }

                /**
                 * Gets the value of the fi360001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getFi360001() {
                    return fi360001;
                }

                /**
                 * Sets the value of the fi360001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setFi360001(Double value) {
                    this.fi360001 = value;
                }

                /**
                 * Gets the value of the ia010401 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIa010401() {
                    return ia010401;
                }

                /**
                 * Sets the value of the ia010401 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIa010401(Double value) {
                    this.ia010401 = value;
                }

                /**
                 * Gets the value of the if010101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getIf010101() {
                    return if010101;
                }

                /**
                 * Sets the value of the if010101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setIf010101(Double value) {
                    this.if010101 = value;
                }

                /**
                 * Gets the value of the mt570001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getMt570001() {
                    return mt570001;
                }

                /**
                 * Sets the value of the mt570001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setMt570001(Double value) {
                    this.mt570001 = value;
                }

                /**
                 * Gets the value of the ps131001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getPs131001() {
                    return ps131001;
                }

                /**
                 * Sets the value of the ps131001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setPs131001(Double value) {
                    this.ps131001 = value;
                }

                /**
                 * Gets the value of the re210001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getRe210001() {
                    return re210001;
                }

                /**
                 * Sets the value of the re210001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setRe210001(Double value) {
                    this.re210001 = value;
                }

                /**
                 * Gets the value of the s0590003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0590003() {
                    return s0590003;
                }

                /**
                 * Sets the value of the s0590003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0590003(Double value) {
                    this.s0590003 = value;
                }

                /**
                 * Gets the value of the s0590004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0590004() {
                    return s0590004;
                }

                /**
                 * Sets the value of the s0590004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0590004(Double value) {
                    this.s0590004 = value;
                }

                /**
                 * Gets the value of the s0620001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0620001() {
                    return s0620001;
                }

                /**
                 * Sets the value of the s0620001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0620001(Double value) {
                    this.s0620001 = value;
                }

                /**
                 * Gets the value of the s0620002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0620002() {
                    return s0620002;
                }

                /**
                 * Sets the value of the s0620002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0620002(Double value) {
                    this.s0620002 = value;
                }

                /**
                 * Gets the value of the s0620003 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0620003() {
                    return s0620003;
                }

                /**
                 * Sets the value of the s0620003 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0620003(Double value) {
                    this.s0620003 = value;
                }

                /**
                 * Gets the value of the s0620004 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0620004() {
                    return s0620004;
                }

                /**
                 * Sets the value of the s0620004 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0620004(Double value) {
                    this.s0620004 = value;
                }

                /**
                 * Gets the value of the s0700001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0700001() {
                    return s0700001;
                }

                /**
                 * Sets the value of the s0700001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0700001(Double value) {
                    this.s0700001 = value;
                }

                /**
                 * Gets the value of the s0700002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS0700002() {
                    return s0700002;
                }

                /**
                 * Sets the value of the s0700002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS0700002(Double value) {
                    this.s0700002 = value;
                }

                /**
                 * Gets the value of the s1150001 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS1150001() {
                    return s1150001;
                }

                /**
                 * Sets the value of the s1150001 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS1150001(Double value) {
                    this.s1150001 = value;
                }

                /**
                 * Gets the value of the s1150002 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getS1150002() {
                    return s1150002;
                }

                /**
                 * Sets the value of the s1150002 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setS1150002(Double value) {
                    this.s1150002 = value;
                }

                /**
                 * Gets the value of the t0201D01 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0201D01() {
                    return t0201D01;
                }

                /**
                 * Sets the value of the t0201D01 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0201D01(Double value) {
                    this.t0201D01 = value;
                }

                /**
                 * Gets the value of the t0201D02 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0201D02() {
                    return t0201D02;
                }

                /**
                 * Sets the value of the t0201D02 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0201D02(Double value) {
                    this.t0201D02 = value;
                }

                /**
                 * Gets the value of the t0201D03 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0201D03() {
                    return t0201D03;
                }

                /**
                 * Sets the value of the t0201D03 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0201D03(Double value) {
                    this.t0201D03 = value;
                }

                /**
                 * Gets the value of the t0301A01 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getT0301A01() {
                    return t0301A01;
                }

                /**
                 * Sets the value of the t0301A01 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setT0301A01(Double value) {
                    this.t0301A01 = value;
                }

                /**
                 * Gets the value of the tf160201 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf160201() {
                    return tf160201;
                }

                /**
                 * Sets the value of the tf160201 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf160201(Double value) {
                    this.tf160201 = value;
                }

                /**
                 * Gets the value of the tf160202 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTf160202() {
                    return tf160202;
                }

                /**
                 * Sets the value of the tf160202 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTf160202(Double value) {
                    this.tf160202 = value;
                }

                /**
                 * Gets the value of the tk080101 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk080101() {
                    return tk080101;
                }

                /**
                 * Sets the value of the tk080101 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk080101(Double value) {
                    this.tk080101 = value;
                }

                /**
                 * Gets the value of the tk080102 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk080102() {
                    return tk080102;
                }

                /**
                 * Sets the value of the tk080102 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk080102(Double value) {
                    this.tk080102 = value;
                }

                /**
                 * Gets the value of the tk080103 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk080103() {
                    return tk080103;
                }

                /**
                 * Sets the value of the tk080103 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk080103(Double value) {
                    this.tk080103 = value;
                }

                /**
                 * Gets the value of the tk080104 property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Double }
                 *     
                 */
                public Double getTk080104() {
                    return tk080104;
                }

                /**
                 * Sets the value of the tk080104 property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Double }
                 *     
                 */
                public void setTk080104(Double value) {
                    this.tk080104 = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="atat" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="at28avg" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="atop06" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="atop12" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="atop18" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="atop24" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="br2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="cb30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="cb3006" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="cb6006" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="g043y" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="in2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pctpbbc" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pctpf" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pctre" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re2801" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re2912" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "atat",
            "at28Avg",
            "atop06",
            "atop12",
            "atop18",
            "atop24",
            "br2801",
            "cb30",
            "cb3006",
            "cb6006",
            "g043Y",
            "in2801",
            "pctpbbc",
            "pctpf",
            "pctre",
            "re2801",
            "re2912"
        })
        public static class Hybrid {

            protected double atat;
            @XmlElement(name = "at28avg")
            protected double at28Avg;
            protected double atop06;
            protected double atop12;
            protected double atop18;
            protected double atop24;
            protected double br2801;
            protected double cb30;
            protected double cb3006;
            protected double cb6006;
            @XmlElement(name = "g043y")
            protected double g043Y;
            protected double in2801;
            protected double pctpbbc;
            protected double pctpf;
            protected double pctre;
            protected double re2801;
            protected double re2912;

            /**
             * Gets the value of the atat property.
             * 
             */
            public double getAtat() {
                return atat;
            }

            /**
             * Sets the value of the atat property.
             * 
             */
            public void setAtat(double value) {
                this.atat = value;
            }

            /**
             * Gets the value of the at28Avg property.
             * 
             */
            public double getAt28Avg() {
                return at28Avg;
            }

            /**
             * Sets the value of the at28Avg property.
             * 
             */
            public void setAt28Avg(double value) {
                this.at28Avg = value;
            }

            /**
             * Gets the value of the atop06 property.
             * 
             */
            public double getAtop06() {
                return atop06;
            }

            /**
             * Sets the value of the atop06 property.
             * 
             */
            public void setAtop06(double value) {
                this.atop06 = value;
            }

            /**
             * Gets the value of the atop12 property.
             * 
             */
            public double getAtop12() {
                return atop12;
            }

            /**
             * Sets the value of the atop12 property.
             * 
             */
            public void setAtop12(double value) {
                this.atop12 = value;
            }

            /**
             * Gets the value of the atop18 property.
             * 
             */
            public double getAtop18() {
                return atop18;
            }

            /**
             * Sets the value of the atop18 property.
             * 
             */
            public void setAtop18(double value) {
                this.atop18 = value;
            }

            /**
             * Gets the value of the atop24 property.
             * 
             */
            public double getAtop24() {
                return atop24;
            }

            /**
             * Sets the value of the atop24 property.
             * 
             */
            public void setAtop24(double value) {
                this.atop24 = value;
            }

            /**
             * Gets the value of the br2801 property.
             * 
             */
            public double getBr2801() {
                return br2801;
            }

            /**
             * Sets the value of the br2801 property.
             * 
             */
            public void setBr2801(double value) {
                this.br2801 = value;
            }

            /**
             * Gets the value of the cb30 property.
             * 
             */
            public double getCb30() {
                return cb30;
            }

            /**
             * Sets the value of the cb30 property.
             * 
             */
            public void setCb30(double value) {
                this.cb30 = value;
            }

            /**
             * Gets the value of the cb3006 property.
             * 
             */
            public double getCb3006() {
                return cb3006;
            }

            /**
             * Sets the value of the cb3006 property.
             * 
             */
            public void setCb3006(double value) {
                this.cb3006 = value;
            }

            /**
             * Gets the value of the cb6006 property.
             * 
             */
            public double getCb6006() {
                return cb6006;
            }

            /**
             * Sets the value of the cb6006 property.
             * 
             */
            public void setCb6006(double value) {
                this.cb6006 = value;
            }

            /**
             * Gets the value of the g043Y property.
             * 
             */
            public double getG043Y() {
                return g043Y;
            }

            /**
             * Sets the value of the g043Y property.
             * 
             */
            public void setG043Y(double value) {
                this.g043Y = value;
            }

            /**
             * Gets the value of the in2801 property.
             * 
             */
            public double getIn2801() {
                return in2801;
            }

            /**
             * Sets the value of the in2801 property.
             * 
             */
            public void setIn2801(double value) {
                this.in2801 = value;
            }

            /**
             * Gets the value of the pctpbbc property.
             * 
             */
            public double getPctpbbc() {
                return pctpbbc;
            }

            /**
             * Sets the value of the pctpbbc property.
             * 
             */
            public void setPctpbbc(double value) {
                this.pctpbbc = value;
            }

            /**
             * Gets the value of the pctpf property.
             * 
             */
            public double getPctpf() {
                return pctpf;
            }

            /**
             * Sets the value of the pctpf property.
             * 
             */
            public void setPctpf(double value) {
                this.pctpf = value;
            }

            /**
             * Gets the value of the pctre property.
             * 
             */
            public double getPctre() {
                return pctre;
            }

            /**
             * Sets the value of the pctre property.
             * 
             */
            public void setPctre(double value) {
                this.pctre = value;
            }

            /**
             * Gets the value of the re2801 property.
             * 
             */
            public double getRe2801() {
                return re2801;
            }

            /**
             * Sets the value of the re2801 property.
             * 
             */
            public void setRe2801(double value) {
                this.re2801 = value;
            }

            /**
             * Gets the value of the re2912 property.
             * 
             */
            public double getRe2912() {
                return re2912;
            }

            /**
             * Sets the value of the re2912 property.
             * 
             */
            public void setRe2912(double value) {
                this.re2912 = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Init {


        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="re_bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_g103" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps141" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_at34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_re03" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_at28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_g051" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps147" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps148" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_br20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_re34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bc20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_at36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_fi20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_in34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps139" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps144" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_at20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_br28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_fr34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_fi36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_re21" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_s062" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_bc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_mt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_ps087" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re_in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "reBc28",
            "reBr32",
            "reG103",
            "reS071",
            "rePs141",
            "reAt34",
            "reRe03",
            "reBc98",
            "reAt28",
            "reFr28",
            "reG051",
            "reRt31",
            "rePf30",
            "rePs147",
            "rePs148",
            "reBi36",
            "reBr20",
            "reMt36",
            "reRe34",
            "reBc20",
            "reAt36",
            "reBc06",
            "reDs35",
            "reAt31",
            "reFi20",
            "reIn34",
            "reMt34",
            "rePs139",
            "rePs144",
            "reRe30",
            "reMt28",
            "reAt20",
            "reBr28",
            "reFr34",
            "reMt02",
            "reBc36",
            "reFi36",
            "reMt57",
            "reRe21",
            "reS062",
            "reS070",
            "reBc30",
            "reMt20",
            "rePs087",
            "reIn31"
        })
        public static class Recode {

            @XmlElement(name = "re_bc28")
            protected double reBc28;
            @XmlElement(name = "re_br32")
            protected double reBr32;
            @XmlElement(name = "re_g103")
            protected double reG103;
            @XmlElement(name = "re_s071")
            protected double reS071;
            @XmlElement(name = "re_ps141")
            protected double rePs141;
            @XmlElement(name = "re_at34")
            protected double reAt34;
            @XmlElement(name = "re_re03")
            protected double reRe03;
            @XmlElement(name = "re_bc98")
            protected double reBc98;
            @XmlElement(name = "re_at28")
            protected double reAt28;
            @XmlElement(name = "re_fr28")
            protected double reFr28;
            @XmlElement(name = "re_g051")
            protected double reG051;
            @XmlElement(name = "re_rt31")
            protected double reRt31;
            @XmlElement(name = "re_pf30")
            protected double rePf30;
            @XmlElement(name = "re_ps147")
            protected double rePs147;
            @XmlElement(name = "re_ps148")
            protected double rePs148;
            @XmlElement(name = "re_bi36")
            protected double reBi36;
            @XmlElement(name = "re_br20")
            protected double reBr20;
            @XmlElement(name = "re_mt36")
            protected double reMt36;
            @XmlElement(name = "re_re34")
            protected double reRe34;
            @XmlElement(name = "re_bc20")
            protected double reBc20;
            @XmlElement(name = "re_at36")
            protected double reAt36;
            @XmlElement(name = "re_bc06")
            protected double reBc06;
            @XmlElement(name = "re_ds35")
            protected double reDs35;
            @XmlElement(name = "re_at31")
            protected double reAt31;
            @XmlElement(name = "re_fi20")
            protected double reFi20;
            @XmlElement(name = "re_in34")
            protected double reIn34;
            @XmlElement(name = "re_mt34")
            protected double reMt34;
            @XmlElement(name = "re_ps139")
            protected double rePs139;
            @XmlElement(name = "re_ps144")
            protected double rePs144;
            @XmlElement(name = "re_re30")
            protected double reRe30;
            @XmlElement(name = "re_mt28")
            protected double reMt28;
            @XmlElement(name = "re_at20")
            protected double reAt20;
            @XmlElement(name = "re_br28")
            protected double reBr28;
            @XmlElement(name = "re_fr34")
            protected double reFr34;
            @XmlElement(name = "re_mt02")
            protected double reMt02;
            @XmlElement(name = "re_bc36")
            protected double reBc36;
            @XmlElement(name = "re_fi36")
            protected double reFi36;
            @XmlElement(name = "re_mt57")
            protected double reMt57;
            @XmlElement(name = "re_re21")
            protected double reRe21;
            @XmlElement(name = "re_s062")
            protected double reS062;
            @XmlElement(name = "re_s070")
            protected double reS070;
            @XmlElement(name = "re_bc30")
            protected double reBc30;
            @XmlElement(name = "re_mt20")
            protected double reMt20;
            @XmlElement(name = "re_ps087")
            protected double rePs087;
            @XmlElement(name = "re_in31")
            protected double reIn31;

            /**
             * Gets the value of the reBc28 property.
             * 
             */
            public double getReBc28() {
                return reBc28;
            }

            /**
             * Sets the value of the reBc28 property.
             * 
             */
            public void setReBc28(double value) {
                this.reBc28 = value;
            }

            /**
             * Gets the value of the reBr32 property.
             * 
             */
            public double getReBr32() {
                return reBr32;
            }

            /**
             * Sets the value of the reBr32 property.
             * 
             */
            public void setReBr32(double value) {
                this.reBr32 = value;
            }

            /**
             * Gets the value of the reG103 property.
             * 
             */
            public double getReG103() {
                return reG103;
            }

            /**
             * Sets the value of the reG103 property.
             * 
             */
            public void setReG103(double value) {
                this.reG103 = value;
            }

            /**
             * Gets the value of the reS071 property.
             * 
             */
            public double getReS071() {
                return reS071;
            }

            /**
             * Sets the value of the reS071 property.
             * 
             */
            public void setReS071(double value) {
                this.reS071 = value;
            }

            /**
             * Gets the value of the rePs141 property.
             * 
             */
            public double getRePs141() {
                return rePs141;
            }

            /**
             * Sets the value of the rePs141 property.
             * 
             */
            public void setRePs141(double value) {
                this.rePs141 = value;
            }

            /**
             * Gets the value of the reAt34 property.
             * 
             */
            public double getReAt34() {
                return reAt34;
            }

            /**
             * Sets the value of the reAt34 property.
             * 
             */
            public void setReAt34(double value) {
                this.reAt34 = value;
            }

            /**
             * Gets the value of the reRe03 property.
             * 
             */
            public double getReRe03() {
                return reRe03;
            }

            /**
             * Sets the value of the reRe03 property.
             * 
             */
            public void setReRe03(double value) {
                this.reRe03 = value;
            }

            /**
             * Gets the value of the reBc98 property.
             * 
             */
            public double getReBc98() {
                return reBc98;
            }

            /**
             * Sets the value of the reBc98 property.
             * 
             */
            public void setReBc98(double value) {
                this.reBc98 = value;
            }

            /**
             * Gets the value of the reAt28 property.
             * 
             */
            public double getReAt28() {
                return reAt28;
            }

            /**
             * Sets the value of the reAt28 property.
             * 
             */
            public void setReAt28(double value) {
                this.reAt28 = value;
            }

            /**
             * Gets the value of the reFr28 property.
             * 
             */
            public double getReFr28() {
                return reFr28;
            }

            /**
             * Sets the value of the reFr28 property.
             * 
             */
            public void setReFr28(double value) {
                this.reFr28 = value;
            }

            /**
             * Gets the value of the reG051 property.
             * 
             */
            public double getReG051() {
                return reG051;
            }

            /**
             * Sets the value of the reG051 property.
             * 
             */
            public void setReG051(double value) {
                this.reG051 = value;
            }

            /**
             * Gets the value of the reRt31 property.
             * 
             */
            public double getReRt31() {
                return reRt31;
            }

            /**
             * Sets the value of the reRt31 property.
             * 
             */
            public void setReRt31(double value) {
                this.reRt31 = value;
            }

            /**
             * Gets the value of the rePf30 property.
             * 
             */
            public double getRePf30() {
                return rePf30;
            }

            /**
             * Sets the value of the rePf30 property.
             * 
             */
            public void setRePf30(double value) {
                this.rePf30 = value;
            }

            /**
             * Gets the value of the rePs147 property.
             * 
             */
            public double getRePs147() {
                return rePs147;
            }

            /**
             * Sets the value of the rePs147 property.
             * 
             */
            public void setRePs147(double value) {
                this.rePs147 = value;
            }

            /**
             * Gets the value of the rePs148 property.
             * 
             */
            public double getRePs148() {
                return rePs148;
            }

            /**
             * Sets the value of the rePs148 property.
             * 
             */
            public void setRePs148(double value) {
                this.rePs148 = value;
            }

            /**
             * Gets the value of the reBi36 property.
             * 
             */
            public double getReBi36() {
                return reBi36;
            }

            /**
             * Sets the value of the reBi36 property.
             * 
             */
            public void setReBi36(double value) {
                this.reBi36 = value;
            }

            /**
             * Gets the value of the reBr20 property.
             * 
             */
            public double getReBr20() {
                return reBr20;
            }

            /**
             * Sets the value of the reBr20 property.
             * 
             */
            public void setReBr20(double value) {
                this.reBr20 = value;
            }

            /**
             * Gets the value of the reMt36 property.
             * 
             */
            public double getReMt36() {
                return reMt36;
            }

            /**
             * Sets the value of the reMt36 property.
             * 
             */
            public void setReMt36(double value) {
                this.reMt36 = value;
            }

            /**
             * Gets the value of the reRe34 property.
             * 
             */
            public double getReRe34() {
                return reRe34;
            }

            /**
             * Sets the value of the reRe34 property.
             * 
             */
            public void setReRe34(double value) {
                this.reRe34 = value;
            }

            /**
             * Gets the value of the reBc20 property.
             * 
             */
            public double getReBc20() {
                return reBc20;
            }

            /**
             * Sets the value of the reBc20 property.
             * 
             */
            public void setReBc20(double value) {
                this.reBc20 = value;
            }

            /**
             * Gets the value of the reAt36 property.
             * 
             */
            public double getReAt36() {
                return reAt36;
            }

            /**
             * Sets the value of the reAt36 property.
             * 
             */
            public void setReAt36(double value) {
                this.reAt36 = value;
            }

            /**
             * Gets the value of the reBc06 property.
             * 
             */
            public double getReBc06() {
                return reBc06;
            }

            /**
             * Sets the value of the reBc06 property.
             * 
             */
            public void setReBc06(double value) {
                this.reBc06 = value;
            }

            /**
             * Gets the value of the reDs35 property.
             * 
             */
            public double getReDs35() {
                return reDs35;
            }

            /**
             * Sets the value of the reDs35 property.
             * 
             */
            public void setReDs35(double value) {
                this.reDs35 = value;
            }

            /**
             * Gets the value of the reAt31 property.
             * 
             */
            public double getReAt31() {
                return reAt31;
            }

            /**
             * Sets the value of the reAt31 property.
             * 
             */
            public void setReAt31(double value) {
                this.reAt31 = value;
            }

            /**
             * Gets the value of the reFi20 property.
             * 
             */
            public double getReFi20() {
                return reFi20;
            }

            /**
             * Sets the value of the reFi20 property.
             * 
             */
            public void setReFi20(double value) {
                this.reFi20 = value;
            }

            /**
             * Gets the value of the reIn34 property.
             * 
             */
            public double getReIn34() {
                return reIn34;
            }

            /**
             * Sets the value of the reIn34 property.
             * 
             */
            public void setReIn34(double value) {
                this.reIn34 = value;
            }

            /**
             * Gets the value of the reMt34 property.
             * 
             */
            public double getReMt34() {
                return reMt34;
            }

            /**
             * Sets the value of the reMt34 property.
             * 
             */
            public void setReMt34(double value) {
                this.reMt34 = value;
            }

            /**
             * Gets the value of the rePs139 property.
             * 
             */
            public double getRePs139() {
                return rePs139;
            }

            /**
             * Sets the value of the rePs139 property.
             * 
             */
            public void setRePs139(double value) {
                this.rePs139 = value;
            }

            /**
             * Gets the value of the rePs144 property.
             * 
             */
            public double getRePs144() {
                return rePs144;
            }

            /**
             * Sets the value of the rePs144 property.
             * 
             */
            public void setRePs144(double value) {
                this.rePs144 = value;
            }

            /**
             * Gets the value of the reRe30 property.
             * 
             */
            public double getReRe30() {
                return reRe30;
            }

            /**
             * Sets the value of the reRe30 property.
             * 
             */
            public void setReRe30(double value) {
                this.reRe30 = value;
            }

            /**
             * Gets the value of the reMt28 property.
             * 
             */
            public double getReMt28() {
                return reMt28;
            }

            /**
             * Sets the value of the reMt28 property.
             * 
             */
            public void setReMt28(double value) {
                this.reMt28 = value;
            }

            /**
             * Gets the value of the reAt20 property.
             * 
             */
            public double getReAt20() {
                return reAt20;
            }

            /**
             * Sets the value of the reAt20 property.
             * 
             */
            public void setReAt20(double value) {
                this.reAt20 = value;
            }

            /**
             * Gets the value of the reBr28 property.
             * 
             */
            public double getReBr28() {
                return reBr28;
            }

            /**
             * Sets the value of the reBr28 property.
             * 
             */
            public void setReBr28(double value) {
                this.reBr28 = value;
            }

            /**
             * Gets the value of the reFr34 property.
             * 
             */
            public double getReFr34() {
                return reFr34;
            }

            /**
             * Sets the value of the reFr34 property.
             * 
             */
            public void setReFr34(double value) {
                this.reFr34 = value;
            }

            /**
             * Gets the value of the reMt02 property.
             * 
             */
            public double getReMt02() {
                return reMt02;
            }

            /**
             * Sets the value of the reMt02 property.
             * 
             */
            public void setReMt02(double value) {
                this.reMt02 = value;
            }

            /**
             * Gets the value of the reBc36 property.
             * 
             */
            public double getReBc36() {
                return reBc36;
            }

            /**
             * Sets the value of the reBc36 property.
             * 
             */
            public void setReBc36(double value) {
                this.reBc36 = value;
            }

            /**
             * Gets the value of the reFi36 property.
             * 
             */
            public double getReFi36() {
                return reFi36;
            }

            /**
             * Sets the value of the reFi36 property.
             * 
             */
            public void setReFi36(double value) {
                this.reFi36 = value;
            }

            /**
             * Gets the value of the reMt57 property.
             * 
             */
            public double getReMt57() {
                return reMt57;
            }

            /**
             * Sets the value of the reMt57 property.
             * 
             */
            public void setReMt57(double value) {
                this.reMt57 = value;
            }

            /**
             * Gets the value of the reRe21 property.
             * 
             */
            public double getReRe21() {
                return reRe21;
            }

            /**
             * Sets the value of the reRe21 property.
             * 
             */
            public void setReRe21(double value) {
                this.reRe21 = value;
            }

            /**
             * Gets the value of the reS062 property.
             * 
             */
            public double getReS062() {
                return reS062;
            }

            /**
             * Sets the value of the reS062 property.
             * 
             */
            public void setReS062(double value) {
                this.reS062 = value;
            }

            /**
             * Gets the value of the reS070 property.
             * 
             */
            public double getReS070() {
                return reS070;
            }

            /**
             * Sets the value of the reS070 property.
             * 
             */
            public void setReS070(double value) {
                this.reS070 = value;
            }

            /**
             * Gets the value of the reBc30 property.
             * 
             */
            public double getReBc30() {
                return reBc30;
            }

            /**
             * Sets the value of the reBc30 property.
             * 
             */
            public void setReBc30(double value) {
                this.reBc30 = value;
            }

            /**
             * Gets the value of the reMt20 property.
             * 
             */
            public double getReMt20() {
                return reMt20;
            }

            /**
             * Sets the value of the reMt20 property.
             * 
             */
            public void setReMt20(double value) {
                this.reMt20 = value;
            }

            /**
             * Gets the value of the rePs087 property.
             * 
             */
            public double getRePs087() {
                return rePs087;
            }

            /**
             * Sets the value of the rePs087 property.
             * 
             */
            public void setRePs087(double value) {
                this.rePs087 = value;
            }

            /**
             * Gets the value of the reIn31 property.
             * 
             */
            public double getReIn31() {
                return reIn31;
            }

            /**
             * Sets the value of the reIn31 property.
             * 
             */
            public void setReIn31(double value) {
                this.reIn31 = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class RecordHeader {


        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="at31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="br01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="br32" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fr01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fr34s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re21s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re29" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="re30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fi20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fi36s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="in01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="in31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pf01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pf30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bc20s" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bc28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="pb01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="rt31" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="ds01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="g960" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="s068" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="s070" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="s071" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="lien24" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="chrgoff" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="repoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="jdsuit12" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="agpub36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="unpdjudg" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="allcollc" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="collec24" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="collec36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="colxmd36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="trdcl6" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="trdutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="auttrds" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="autoutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="autnutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="rethibal" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="rethccl" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fioactth" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="fiagblth" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="firatio" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="firatoth" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="revratio" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkrcrdlt" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkrutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkopbal6" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkratth" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bktrades" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkagbal" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bkutil" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="bankmof" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="colinq12" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="colinq" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="retavgbl" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat01" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *         &lt;element name="scrat03" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat06" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat07" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat08" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat09" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat11" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat25" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat26" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat27" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrat36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbr20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbr28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrre03" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrre12" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrre28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrre34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrfi01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrin28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrin34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt02" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt20" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt28" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt34" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrmt57" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbc01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbc06" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbc30" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbc36" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrbc98" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrrt01" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrds35" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg017" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg041" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg042" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg043" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg049" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg051" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg059" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg063" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg068" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg091" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg093" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg094" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg103" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs004" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs019" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs043" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs046" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs059" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs062" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs064" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrs115" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg069" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *         &lt;element name="scrg058" type="{http://www.w3.org/2001/XMLSchema}double"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "at31",
            "br01",
            "br32",
            "fr01",
            "fr28",
            "fr34S",
            "re01",
            "re21S",
            "re29",
            "re30",
            "bi36S",
            "fi20S",
            "fi36S",
            "in01",
            "in31",
            "pf01",
            "pf30",
            "bc20S",
            "bc28",
            "pb01",
            "rt31",
            "ds01",
            "g960",
            "s068",
            "s070",
            "s071",
            "lien24",
            "chrgoff",
            "repoth",
            "jdsuit12",
            "agpub36",
            "unpdjudg",
            "allcollc",
            "collec24",
            "collec36",
            "colxmd36",
            "trdcl6",
            "trdutil",
            "auttrds",
            "autoutil",
            "autnutil",
            "rethibal",
            "rethccl",
            "fioactth",
            "fiagblth",
            "firatio",
            "firatoth",
            "revratio",
            "bkrcrdlt",
            "bkrutil",
            "bkopbal6",
            "bkratth",
            "bktrades",
            "bkagbal",
            "bkutil",
            "bankmof",
            "colinq12",
            "colinq",
            "retavgbl",
            "scrat01",
            "scrat03",
            "scrat06",
            "scrat07",
            "scrat08",
            "scrat09",
            "scrat11",
            "scrat20",
            "scrat25",
            "scrat26",
            "scrat27",
            "scrat28",
            "scrat34",
            "scrat36",
            "scrbr20",
            "scrbr28",
            "scrre03",
            "scrre12",
            "scrre28",
            "scrre34",
            "scrbi01",
            "scrfi01",
            "scrin28",
            "scrin34",
            "scrmt01",
            "scrmt02",
            "scrmt20",
            "scrmt28",
            "scrmt34",
            "scrmt36",
            "scrmt57",
            "scrbc01",
            "scrbc06",
            "scrbc30",
            "scrbc36",
            "scrbc98",
            "scrrt01",
            "scrds35",
            "scrg017",
            "scrg041",
            "scrg042",
            "scrg043",
            "scrg049",
            "scrg051",
            "scrg059",
            "scrg063",
            "scrg068",
            "scrg091",
            "scrg093",
            "scrg094",
            "scrg103",
            "scrs004",
            "scrs019",
            "scrs043",
            "scrs046",
            "scrs059",
            "scrs062",
            "scrs064",
            "scrs115",
            "scrg069",
            "scrg058"
        })
        public static class Variables {

            protected double at31;
            protected double br01;
            protected double br32;
            protected double fr01;
            protected double fr28;
            @XmlElement(name = "fr34s")
            protected double fr34S;
            protected double re01;
            @XmlElement(name = "re21s")
            protected double re21S;
            protected double re29;
            protected double re30;
            @XmlElement(name = "bi36s")
            protected double bi36S;
            @XmlElement(name = "fi20s")
            protected double fi20S;
            @XmlElement(name = "fi36s")
            protected double fi36S;
            protected double in01;
            protected double in31;
            protected double pf01;
            protected double pf30;
            @XmlElement(name = "bc20s")
            protected double bc20S;
            protected double bc28;
            protected double pb01;
            protected double rt31;
            protected double ds01;
            protected double g960;
            protected double s068;
            protected double s070;
            protected double s071;
            protected double lien24;
            protected double chrgoff;
            protected double repoth;
            protected double jdsuit12;
            protected double agpub36;
            protected double unpdjudg;
            protected double allcollc;
            protected double collec24;
            protected double collec36;
            protected double colxmd36;
            protected double trdcl6;
            protected double trdutil;
            protected double auttrds;
            protected double autoutil;
            protected double autnutil;
            protected double rethibal;
            protected double rethccl;
            protected double fioactth;
            protected double fiagblth;
            protected double firatio;
            protected double firatoth;
            protected double revratio;
            protected double bkrcrdlt;
            protected double bkrutil;
            protected double bkopbal6;
            protected double bkratth;
            protected double bktrades;
            protected double bkagbal;
            protected double bkutil;
            protected double bankmof;
            protected double colinq12;
            protected double colinq;
            protected double retavgbl;
            protected int scrat01;
            protected double scrat03;
            protected double scrat06;
            protected double scrat07;
            protected double scrat08;
            protected double scrat09;
            protected double scrat11;
            protected double scrat20;
            protected double scrat25;
            protected double scrat26;
            protected double scrat27;
            protected double scrat28;
            protected double scrat34;
            protected double scrat36;
            protected double scrbr20;
            protected double scrbr28;
            protected double scrre03;
            protected double scrre12;
            protected double scrre28;
            protected double scrre34;
            protected double scrbi01;
            protected double scrfi01;
            protected double scrin28;
            protected double scrin34;
            protected double scrmt01;
            protected double scrmt02;
            protected double scrmt20;
            protected double scrmt28;
            protected double scrmt34;
            protected double scrmt36;
            protected double scrmt57;
            protected double scrbc01;
            protected double scrbc06;
            protected double scrbc30;
            protected double scrbc36;
            protected double scrbc98;
            protected double scrrt01;
            protected double scrds35;
            protected double scrg017;
            protected double scrg041;
            protected double scrg042;
            protected double scrg043;
            protected double scrg049;
            protected double scrg051;
            protected double scrg059;
            protected double scrg063;
            protected double scrg068;
            protected double scrg091;
            protected double scrg093;
            protected double scrg094;
            protected double scrg103;
            protected double scrs004;
            protected double scrs019;
            protected double scrs043;
            protected double scrs046;
            protected double scrs059;
            protected double scrs062;
            protected double scrs064;
            protected double scrs115;
            protected double scrg069;
            protected double scrg058;

            /**
             * Gets the value of the at31 property.
             * 
             */
            public double getAt31() {
                return at31;
            }

            /**
             * Sets the value of the at31 property.
             * 
             */
            public void setAt31(double value) {
                this.at31 = value;
            }

            /**
             * Gets the value of the br01 property.
             * 
             */
            public double getBr01() {
                return br01;
            }

            /**
             * Sets the value of the br01 property.
             * 
             */
            public void setBr01(double value) {
                this.br01 = value;
            }

            /**
             * Gets the value of the br32 property.
             * 
             */
            public double getBr32() {
                return br32;
            }

            /**
             * Sets the value of the br32 property.
             * 
             */
            public void setBr32(double value) {
                this.br32 = value;
            }

            /**
             * Gets the value of the fr01 property.
             * 
             */
            public double getFr01() {
                return fr01;
            }

            /**
             * Sets the value of the fr01 property.
             * 
             */
            public void setFr01(double value) {
                this.fr01 = value;
            }

            /**
             * Gets the value of the fr28 property.
             * 
             */
            public double getFr28() {
                return fr28;
            }

            /**
             * Sets the value of the fr28 property.
             * 
             */
            public void setFr28(double value) {
                this.fr28 = value;
            }

            /**
             * Gets the value of the fr34S property.
             * 
             */
            public double getFr34S() {
                return fr34S;
            }

            /**
             * Sets the value of the fr34S property.
             * 
             */
            public void setFr34S(double value) {
                this.fr34S = value;
            }

            /**
             * Gets the value of the re01 property.
             * 
             */
            public double getRe01() {
                return re01;
            }

            /**
             * Sets the value of the re01 property.
             * 
             */
            public void setRe01(double value) {
                this.re01 = value;
            }

            /**
             * Gets the value of the re21S property.
             * 
             */
            public double getRe21S() {
                return re21S;
            }

            /**
             * Sets the value of the re21S property.
             * 
             */
            public void setRe21S(double value) {
                this.re21S = value;
            }

            /**
             * Gets the value of the re29 property.
             * 
             */
            public double getRe29() {
                return re29;
            }

            /**
             * Sets the value of the re29 property.
             * 
             */
            public void setRe29(double value) {
                this.re29 = value;
            }

            /**
             * Gets the value of the re30 property.
             * 
             */
            public double getRe30() {
                return re30;
            }

            /**
             * Sets the value of the re30 property.
             * 
             */
            public void setRe30(double value) {
                this.re30 = value;
            }

            /**
             * Gets the value of the bi36S property.
             * 
             */
            public double getBi36S() {
                return bi36S;
            }

            /**
             * Sets the value of the bi36S property.
             * 
             */
            public void setBi36S(double value) {
                this.bi36S = value;
            }

            /**
             * Gets the value of the fi20S property.
             * 
             */
            public double getFi20S() {
                return fi20S;
            }

            /**
             * Sets the value of the fi20S property.
             * 
             */
            public void setFi20S(double value) {
                this.fi20S = value;
            }

            /**
             * Gets the value of the fi36S property.
             * 
             */
            public double getFi36S() {
                return fi36S;
            }

            /**
             * Sets the value of the fi36S property.
             * 
             */
            public void setFi36S(double value) {
                this.fi36S = value;
            }

            /**
             * Gets the value of the in01 property.
             * 
             */
            public double getIn01() {
                return in01;
            }

            /**
             * Sets the value of the in01 property.
             * 
             */
            public void setIn01(double value) {
                this.in01 = value;
            }

            /**
             * Gets the value of the in31 property.
             * 
             */
            public double getIn31() {
                return in31;
            }

            /**
             * Sets the value of the in31 property.
             * 
             */
            public void setIn31(double value) {
                this.in31 = value;
            }

            /**
             * Gets the value of the pf01 property.
             * 
             */
            public double getPf01() {
                return pf01;
            }

            /**
             * Sets the value of the pf01 property.
             * 
             */
            public void setPf01(double value) {
                this.pf01 = value;
            }

            /**
             * Gets the value of the pf30 property.
             * 
             */
            public double getPf30() {
                return pf30;
            }

            /**
             * Sets the value of the pf30 property.
             * 
             */
            public void setPf30(double value) {
                this.pf30 = value;
            }

            /**
             * Gets the value of the bc20S property.
             * 
             */
            public double getBc20S() {
                return bc20S;
            }

            /**
             * Sets the value of the bc20S property.
             * 
             */
            public void setBc20S(double value) {
                this.bc20S = value;
            }

            /**
             * Gets the value of the bc28 property.
             * 
             */
            public double getBc28() {
                return bc28;
            }

            /**
             * Sets the value of the bc28 property.
             * 
             */
            public void setBc28(double value) {
                this.bc28 = value;
            }

            /**
             * Gets the value of the pb01 property.
             * 
             */
            public double getPb01() {
                return pb01;
            }

            /**
             * Sets the value of the pb01 property.
             * 
             */
            public void setPb01(double value) {
                this.pb01 = value;
            }

            /**
             * Gets the value of the rt31 property.
             * 
             */
            public double getRt31() {
                return rt31;
            }

            /**
             * Sets the value of the rt31 property.
             * 
             */
            public void setRt31(double value) {
                this.rt31 = value;
            }

            /**
             * Gets the value of the ds01 property.
             * 
             */
            public double getDs01() {
                return ds01;
            }

            /**
             * Sets the value of the ds01 property.
             * 
             */
            public void setDs01(double value) {
                this.ds01 = value;
            }

            /**
             * Gets the value of the g960 property.
             * 
             */
            public double getG960() {
                return g960;
            }

            /**
             * Sets the value of the g960 property.
             * 
             */
            public void setG960(double value) {
                this.g960 = value;
            }

            /**
             * Gets the value of the s068 property.
             * 
             */
            public double getS068() {
                return s068;
            }

            /**
             * Sets the value of the s068 property.
             * 
             */
            public void setS068(double value) {
                this.s068 = value;
            }

            /**
             * Gets the value of the s070 property.
             * 
             */
            public double getS070() {
                return s070;
            }

            /**
             * Sets the value of the s070 property.
             * 
             */
            public void setS070(double value) {
                this.s070 = value;
            }

            /**
             * Gets the value of the s071 property.
             * 
             */
            public double getS071() {
                return s071;
            }

            /**
             * Sets the value of the s071 property.
             * 
             */
            public void setS071(double value) {
                this.s071 = value;
            }

            /**
             * Gets the value of the lien24 property.
             * 
             */
            public double getLien24() {
                return lien24;
            }

            /**
             * Sets the value of the lien24 property.
             * 
             */
            public void setLien24(double value) {
                this.lien24 = value;
            }

            /**
             * Gets the value of the chrgoff property.
             * 
             */
            public double getChrgoff() {
                return chrgoff;
            }

            /**
             * Sets the value of the chrgoff property.
             * 
             */
            public void setChrgoff(double value) {
                this.chrgoff = value;
            }

            /**
             * Gets the value of the repoth property.
             * 
             */
            public double getRepoth() {
                return repoth;
            }

            /**
             * Sets the value of the repoth property.
             * 
             */
            public void setRepoth(double value) {
                this.repoth = value;
            }

            /**
             * Gets the value of the jdsuit12 property.
             * 
             */
            public double getJdsuit12() {
                return jdsuit12;
            }

            /**
             * Sets the value of the jdsuit12 property.
             * 
             */
            public void setJdsuit12(double value) {
                this.jdsuit12 = value;
            }

            /**
             * Gets the value of the agpub36 property.
             * 
             */
            public double getAgpub36() {
                return agpub36;
            }

            /**
             * Sets the value of the agpub36 property.
             * 
             */
            public void setAgpub36(double value) {
                this.agpub36 = value;
            }

            /**
             * Gets the value of the unpdjudg property.
             * 
             */
            public double getUnpdjudg() {
                return unpdjudg;
            }

            /**
             * Sets the value of the unpdjudg property.
             * 
             */
            public void setUnpdjudg(double value) {
                this.unpdjudg = value;
            }

            /**
             * Gets the value of the allcollc property.
             * 
             */
            public double getAllcollc() {
                return allcollc;
            }

            /**
             * Sets the value of the allcollc property.
             * 
             */
            public void setAllcollc(double value) {
                this.allcollc = value;
            }

            /**
             * Gets the value of the collec24 property.
             * 
             */
            public double getCollec24() {
                return collec24;
            }

            /**
             * Sets the value of the collec24 property.
             * 
             */
            public void setCollec24(double value) {
                this.collec24 = value;
            }

            /**
             * Gets the value of the collec36 property.
             * 
             */
            public double getCollec36() {
                return collec36;
            }

            /**
             * Sets the value of the collec36 property.
             * 
             */
            public void setCollec36(double value) {
                this.collec36 = value;
            }

            /**
             * Gets the value of the colxmd36 property.
             * 
             */
            public double getColxmd36() {
                return colxmd36;
            }

            /**
             * Sets the value of the colxmd36 property.
             * 
             */
            public void setColxmd36(double value) {
                this.colxmd36 = value;
            }

            /**
             * Gets the value of the trdcl6 property.
             * 
             */
            public double getTrdcl6() {
                return trdcl6;
            }

            /**
             * Sets the value of the trdcl6 property.
             * 
             */
            public void setTrdcl6(double value) {
                this.trdcl6 = value;
            }

            /**
             * Gets the value of the trdutil property.
             * 
             */
            public double getTrdutil() {
                return trdutil;
            }

            /**
             * Sets the value of the trdutil property.
             * 
             */
            public void setTrdutil(double value) {
                this.trdutil = value;
            }

            /**
             * Gets the value of the auttrds property.
             * 
             */
            public double getAuttrds() {
                return auttrds;
            }

            /**
             * Sets the value of the auttrds property.
             * 
             */
            public void setAuttrds(double value) {
                this.auttrds = value;
            }

            /**
             * Gets the value of the autoutil property.
             * 
             */
            public double getAutoutil() {
                return autoutil;
            }

            /**
             * Sets the value of the autoutil property.
             * 
             */
            public void setAutoutil(double value) {
                this.autoutil = value;
            }

            /**
             * Gets the value of the autnutil property.
             * 
             */
            public double getAutnutil() {
                return autnutil;
            }

            /**
             * Sets the value of the autnutil property.
             * 
             */
            public void setAutnutil(double value) {
                this.autnutil = value;
            }

            /**
             * Gets the value of the rethibal property.
             * 
             */
            public double getRethibal() {
                return rethibal;
            }

            /**
             * Sets the value of the rethibal property.
             * 
             */
            public void setRethibal(double value) {
                this.rethibal = value;
            }

            /**
             * Gets the value of the rethccl property.
             * 
             */
            public double getRethccl() {
                return rethccl;
            }

            /**
             * Sets the value of the rethccl property.
             * 
             */
            public void setRethccl(double value) {
                this.rethccl = value;
            }

            /**
             * Gets the value of the fioactth property.
             * 
             */
            public double getFioactth() {
                return fioactth;
            }

            /**
             * Sets the value of the fioactth property.
             * 
             */
            public void setFioactth(double value) {
                this.fioactth = value;
            }

            /**
             * Gets the value of the fiagblth property.
             * 
             */
            public double getFiagblth() {
                return fiagblth;
            }

            /**
             * Sets the value of the fiagblth property.
             * 
             */
            public void setFiagblth(double value) {
                this.fiagblth = value;
            }

            /**
             * Gets the value of the firatio property.
             * 
             */
            public double getFiratio() {
                return firatio;
            }

            /**
             * Sets the value of the firatio property.
             * 
             */
            public void setFiratio(double value) {
                this.firatio = value;
            }

            /**
             * Gets the value of the firatoth property.
             * 
             */
            public double getFiratoth() {
                return firatoth;
            }

            /**
             * Sets the value of the firatoth property.
             * 
             */
            public void setFiratoth(double value) {
                this.firatoth = value;
            }

            /**
             * Gets the value of the revratio property.
             * 
             */
            public double getRevratio() {
                return revratio;
            }

            /**
             * Sets the value of the revratio property.
             * 
             */
            public void setRevratio(double value) {
                this.revratio = value;
            }

            /**
             * Gets the value of the bkrcrdlt property.
             * 
             */
            public double getBkrcrdlt() {
                return bkrcrdlt;
            }

            /**
             * Sets the value of the bkrcrdlt property.
             * 
             */
            public void setBkrcrdlt(double value) {
                this.bkrcrdlt = value;
            }

            /**
             * Gets the value of the bkrutil property.
             * 
             */
            public double getBkrutil() {
                return bkrutil;
            }

            /**
             * Sets the value of the bkrutil property.
             * 
             */
            public void setBkrutil(double value) {
                this.bkrutil = value;
            }

            /**
             * Gets the value of the bkopbal6 property.
             * 
             */
            public double getBkopbal6() {
                return bkopbal6;
            }

            /**
             * Sets the value of the bkopbal6 property.
             * 
             */
            public void setBkopbal6(double value) {
                this.bkopbal6 = value;
            }

            /**
             * Gets the value of the bkratth property.
             * 
             */
            public double getBkratth() {
                return bkratth;
            }

            /**
             * Sets the value of the bkratth property.
             * 
             */
            public void setBkratth(double value) {
                this.bkratth = value;
            }

            /**
             * Gets the value of the bktrades property.
             * 
             */
            public double getBktrades() {
                return bktrades;
            }

            /**
             * Sets the value of the bktrades property.
             * 
             */
            public void setBktrades(double value) {
                this.bktrades = value;
            }

            /**
             * Gets the value of the bkagbal property.
             * 
             */
            public double getBkagbal() {
                return bkagbal;
            }

            /**
             * Sets the value of the bkagbal property.
             * 
             */
            public void setBkagbal(double value) {
                this.bkagbal = value;
            }

            /**
             * Gets the value of the bkutil property.
             * 
             */
            public double getBkutil() {
                return bkutil;
            }

            /**
             * Sets the value of the bkutil property.
             * 
             */
            public void setBkutil(double value) {
                this.bkutil = value;
            }

            /**
             * Gets the value of the bankmof property.
             * 
             */
            public double getBankmof() {
                return bankmof;
            }

            /**
             * Sets the value of the bankmof property.
             * 
             */
            public void setBankmof(double value) {
                this.bankmof = value;
            }

            /**
             * Gets the value of the colinq12 property.
             * 
             */
            public double getColinq12() {
                return colinq12;
            }

            /**
             * Sets the value of the colinq12 property.
             * 
             */
            public void setColinq12(double value) {
                this.colinq12 = value;
            }

            /**
             * Gets the value of the colinq property.
             * 
             */
            public double getColinq() {
                return colinq;
            }

            /**
             * Sets the value of the colinq property.
             * 
             */
            public void setColinq(double value) {
                this.colinq = value;
            }

            /**
             * Gets the value of the retavgbl property.
             * 
             */
            public double getRetavgbl() {
                return retavgbl;
            }

            /**
             * Sets the value of the retavgbl property.
             * 
             */
            public void setRetavgbl(double value) {
                this.retavgbl = value;
            }

            /**
             * Gets the value of the scrat01 property.
             * 
             */
            public int getScrat01() {
                return scrat01;
            }

            /**
             * Sets the value of the scrat01 property.
             * 
             */
            public void setScrat01(int value) {
                this.scrat01 = value;
            }

            /**
             * Gets the value of the scrat03 property.
             * 
             */
            public double getScrat03() {
                return scrat03;
            }

            /**
             * Sets the value of the scrat03 property.
             * 
             */
            public void setScrat03(double value) {
                this.scrat03 = value;
            }

            /**
             * Gets the value of the scrat06 property.
             * 
             */
            public double getScrat06() {
                return scrat06;
            }

            /**
             * Sets the value of the scrat06 property.
             * 
             */
            public void setScrat06(double value) {
                this.scrat06 = value;
            }

            /**
             * Gets the value of the scrat07 property.
             * 
             */
            public double getScrat07() {
                return scrat07;
            }

            /**
             * Sets the value of the scrat07 property.
             * 
             */
            public void setScrat07(double value) {
                this.scrat07 = value;
            }

            /**
             * Gets the value of the scrat08 property.
             * 
             */
            public double getScrat08() {
                return scrat08;
            }

            /**
             * Sets the value of the scrat08 property.
             * 
             */
            public void setScrat08(double value) {
                this.scrat08 = value;
            }

            /**
             * Gets the value of the scrat09 property.
             * 
             */
            public double getScrat09() {
                return scrat09;
            }

            /**
             * Sets the value of the scrat09 property.
             * 
             */
            public void setScrat09(double value) {
                this.scrat09 = value;
            }

            /**
             * Gets the value of the scrat11 property.
             * 
             */
            public double getScrat11() {
                return scrat11;
            }

            /**
             * Sets the value of the scrat11 property.
             * 
             */
            public void setScrat11(double value) {
                this.scrat11 = value;
            }

            /**
             * Gets the value of the scrat20 property.
             * 
             */
            public double getScrat20() {
                return scrat20;
            }

            /**
             * Sets the value of the scrat20 property.
             * 
             */
            public void setScrat20(double value) {
                this.scrat20 = value;
            }

            /**
             * Gets the value of the scrat25 property.
             * 
             */
            public double getScrat25() {
                return scrat25;
            }

            /**
             * Sets the value of the scrat25 property.
             * 
             */
            public void setScrat25(double value) {
                this.scrat25 = value;
            }

            /**
             * Gets the value of the scrat26 property.
             * 
             */
            public double getScrat26() {
                return scrat26;
            }

            /**
             * Sets the value of the scrat26 property.
             * 
             */
            public void setScrat26(double value) {
                this.scrat26 = value;
            }

            /**
             * Gets the value of the scrat27 property.
             * 
             */
            public double getScrat27() {
                return scrat27;
            }

            /**
             * Sets the value of the scrat27 property.
             * 
             */
            public void setScrat27(double value) {
                this.scrat27 = value;
            }

            /**
             * Gets the value of the scrat28 property.
             * 
             */
            public double getScrat28() {
                return scrat28;
            }

            /**
             * Sets the value of the scrat28 property.
             * 
             */
            public void setScrat28(double value) {
                this.scrat28 = value;
            }

            /**
             * Gets the value of the scrat34 property.
             * 
             */
            public double getScrat34() {
                return scrat34;
            }

            /**
             * Sets the value of the scrat34 property.
             * 
             */
            public void setScrat34(double value) {
                this.scrat34 = value;
            }

            /**
             * Gets the value of the scrat36 property.
             * 
             */
            public double getScrat36() {
                return scrat36;
            }

            /**
             * Sets the value of the scrat36 property.
             * 
             */
            public void setScrat36(double value) {
                this.scrat36 = value;
            }

            /**
             * Gets the value of the scrbr20 property.
             * 
             */
            public double getScrbr20() {
                return scrbr20;
            }

            /**
             * Sets the value of the scrbr20 property.
             * 
             */
            public void setScrbr20(double value) {
                this.scrbr20 = value;
            }

            /**
             * Gets the value of the scrbr28 property.
             * 
             */
            public double getScrbr28() {
                return scrbr28;
            }

            /**
             * Sets the value of the scrbr28 property.
             * 
             */
            public void setScrbr28(double value) {
                this.scrbr28 = value;
            }

            /**
             * Gets the value of the scrre03 property.
             * 
             */
            public double getScrre03() {
                return scrre03;
            }

            /**
             * Sets the value of the scrre03 property.
             * 
             */
            public void setScrre03(double value) {
                this.scrre03 = value;
            }

            /**
             * Gets the value of the scrre12 property.
             * 
             */
            public double getScrre12() {
                return scrre12;
            }

            /**
             * Sets the value of the scrre12 property.
             * 
             */
            public void setScrre12(double value) {
                this.scrre12 = value;
            }

            /**
             * Gets the value of the scrre28 property.
             * 
             */
            public double getScrre28() {
                return scrre28;
            }

            /**
             * Sets the value of the scrre28 property.
             * 
             */
            public void setScrre28(double value) {
                this.scrre28 = value;
            }

            /**
             * Gets the value of the scrre34 property.
             * 
             */
            public double getScrre34() {
                return scrre34;
            }

            /**
             * Sets the value of the scrre34 property.
             * 
             */
            public void setScrre34(double value) {
                this.scrre34 = value;
            }

            /**
             * Gets the value of the scrbi01 property.
             * 
             */
            public double getScrbi01() {
                return scrbi01;
            }

            /**
             * Sets the value of the scrbi01 property.
             * 
             */
            public void setScrbi01(double value) {
                this.scrbi01 = value;
            }

            /**
             * Gets the value of the scrfi01 property.
             * 
             */
            public double getScrfi01() {
                return scrfi01;
            }

            /**
             * Sets the value of the scrfi01 property.
             * 
             */
            public void setScrfi01(double value) {
                this.scrfi01 = value;
            }

            /**
             * Gets the value of the scrin28 property.
             * 
             */
            public double getScrin28() {
                return scrin28;
            }

            /**
             * Sets the value of the scrin28 property.
             * 
             */
            public void setScrin28(double value) {
                this.scrin28 = value;
            }

            /**
             * Gets the value of the scrin34 property.
             * 
             */
            public double getScrin34() {
                return scrin34;
            }

            /**
             * Sets the value of the scrin34 property.
             * 
             */
            public void setScrin34(double value) {
                this.scrin34 = value;
            }

            /**
             * Gets the value of the scrmt01 property.
             * 
             */
            public double getScrmt01() {
                return scrmt01;
            }

            /**
             * Sets the value of the scrmt01 property.
             * 
             */
            public void setScrmt01(double value) {
                this.scrmt01 = value;
            }

            /**
             * Gets the value of the scrmt02 property.
             * 
             */
            public double getScrmt02() {
                return scrmt02;
            }

            /**
             * Sets the value of the scrmt02 property.
             * 
             */
            public void setScrmt02(double value) {
                this.scrmt02 = value;
            }

            /**
             * Gets the value of the scrmt20 property.
             * 
             */
            public double getScrmt20() {
                return scrmt20;
            }

            /**
             * Sets the value of the scrmt20 property.
             * 
             */
            public void setScrmt20(double value) {
                this.scrmt20 = value;
            }

            /**
             * Gets the value of the scrmt28 property.
             * 
             */
            public double getScrmt28() {
                return scrmt28;
            }

            /**
             * Sets the value of the scrmt28 property.
             * 
             */
            public void setScrmt28(double value) {
                this.scrmt28 = value;
            }

            /**
             * Gets the value of the scrmt34 property.
             * 
             */
            public double getScrmt34() {
                return scrmt34;
            }

            /**
             * Sets the value of the scrmt34 property.
             * 
             */
            public void setScrmt34(double value) {
                this.scrmt34 = value;
            }

            /**
             * Gets the value of the scrmt36 property.
             * 
             */
            public double getScrmt36() {
                return scrmt36;
            }

            /**
             * Sets the value of the scrmt36 property.
             * 
             */
            public void setScrmt36(double value) {
                this.scrmt36 = value;
            }

            /**
             * Gets the value of the scrmt57 property.
             * 
             */
            public double getScrmt57() {
                return scrmt57;
            }

            /**
             * Sets the value of the scrmt57 property.
             * 
             */
            public void setScrmt57(double value) {
                this.scrmt57 = value;
            }

            /**
             * Gets the value of the scrbc01 property.
             * 
             */
            public double getScrbc01() {
                return scrbc01;
            }

            /**
             * Sets the value of the scrbc01 property.
             * 
             */
            public void setScrbc01(double value) {
                this.scrbc01 = value;
            }

            /**
             * Gets the value of the scrbc06 property.
             * 
             */
            public double getScrbc06() {
                return scrbc06;
            }

            /**
             * Sets the value of the scrbc06 property.
             * 
             */
            public void setScrbc06(double value) {
                this.scrbc06 = value;
            }

            /**
             * Gets the value of the scrbc30 property.
             * 
             */
            public double getScrbc30() {
                return scrbc30;
            }

            /**
             * Sets the value of the scrbc30 property.
             * 
             */
            public void setScrbc30(double value) {
                this.scrbc30 = value;
            }

            /**
             * Gets the value of the scrbc36 property.
             * 
             */
            public double getScrbc36() {
                return scrbc36;
            }

            /**
             * Sets the value of the scrbc36 property.
             * 
             */
            public void setScrbc36(double value) {
                this.scrbc36 = value;
            }

            /**
             * Gets the value of the scrbc98 property.
             * 
             */
            public double getScrbc98() {
                return scrbc98;
            }

            /**
             * Sets the value of the scrbc98 property.
             * 
             */
            public void setScrbc98(double value) {
                this.scrbc98 = value;
            }

            /**
             * Gets the value of the scrrt01 property.
             * 
             */
            public double getScrrt01() {
                return scrrt01;
            }

            /**
             * Sets the value of the scrrt01 property.
             * 
             */
            public void setScrrt01(double value) {
                this.scrrt01 = value;
            }

            /**
             * Gets the value of the scrds35 property.
             * 
             */
            public double getScrds35() {
                return scrds35;
            }

            /**
             * Sets the value of the scrds35 property.
             * 
             */
            public void setScrds35(double value) {
                this.scrds35 = value;
            }

            /**
             * Gets the value of the scrg017 property.
             * 
             */
            public double getScrg017() {
                return scrg017;
            }

            /**
             * Sets the value of the scrg017 property.
             * 
             */
            public void setScrg017(double value) {
                this.scrg017 = value;
            }

            /**
             * Gets the value of the scrg041 property.
             * 
             */
            public double getScrg041() {
                return scrg041;
            }

            /**
             * Sets the value of the scrg041 property.
             * 
             */
            public void setScrg041(double value) {
                this.scrg041 = value;
            }

            /**
             * Gets the value of the scrg042 property.
             * 
             */
            public double getScrg042() {
                return scrg042;
            }

            /**
             * Sets the value of the scrg042 property.
             * 
             */
            public void setScrg042(double value) {
                this.scrg042 = value;
            }

            /**
             * Gets the value of the scrg043 property.
             * 
             */
            public double getScrg043() {
                return scrg043;
            }

            /**
             * Sets the value of the scrg043 property.
             * 
             */
            public void setScrg043(double value) {
                this.scrg043 = value;
            }

            /**
             * Gets the value of the scrg049 property.
             * 
             */
            public double getScrg049() {
                return scrg049;
            }

            /**
             * Sets the value of the scrg049 property.
             * 
             */
            public void setScrg049(double value) {
                this.scrg049 = value;
            }

            /**
             * Gets the value of the scrg051 property.
             * 
             */
            public double getScrg051() {
                return scrg051;
            }

            /**
             * Sets the value of the scrg051 property.
             * 
             */
            public void setScrg051(double value) {
                this.scrg051 = value;
            }

            /**
             * Gets the value of the scrg059 property.
             * 
             */
            public double getScrg059() {
                return scrg059;
            }

            /**
             * Sets the value of the scrg059 property.
             * 
             */
            public void setScrg059(double value) {
                this.scrg059 = value;
            }

            /**
             * Gets the value of the scrg063 property.
             * 
             */
            public double getScrg063() {
                return scrg063;
            }

            /**
             * Sets the value of the scrg063 property.
             * 
             */
            public void setScrg063(double value) {
                this.scrg063 = value;
            }

            /**
             * Gets the value of the scrg068 property.
             * 
             */
            public double getScrg068() {
                return scrg068;
            }

            /**
             * Sets the value of the scrg068 property.
             * 
             */
            public void setScrg068(double value) {
                this.scrg068 = value;
            }

            /**
             * Gets the value of the scrg091 property.
             * 
             */
            public double getScrg091() {
                return scrg091;
            }

            /**
             * Sets the value of the scrg091 property.
             * 
             */
            public void setScrg091(double value) {
                this.scrg091 = value;
            }

            /**
             * Gets the value of the scrg093 property.
             * 
             */
            public double getScrg093() {
                return scrg093;
            }

            /**
             * Sets the value of the scrg093 property.
             * 
             */
            public void setScrg093(double value) {
                this.scrg093 = value;
            }

            /**
             * Gets the value of the scrg094 property.
             * 
             */
            public double getScrg094() {
                return scrg094;
            }

            /**
             * Sets the value of the scrg094 property.
             * 
             */
            public void setScrg094(double value) {
                this.scrg094 = value;
            }

            /**
             * Gets the value of the scrg103 property.
             * 
             */
            public double getScrg103() {
                return scrg103;
            }

            /**
             * Sets the value of the scrg103 property.
             * 
             */
            public void setScrg103(double value) {
                this.scrg103 = value;
            }

            /**
             * Gets the value of the scrs004 property.
             * 
             */
            public double getScrs004() {
                return scrs004;
            }

            /**
             * Sets the value of the scrs004 property.
             * 
             */
            public void setScrs004(double value) {
                this.scrs004 = value;
            }

            /**
             * Gets the value of the scrs019 property.
             * 
             */
            public double getScrs019() {
                return scrs019;
            }

            /**
             * Sets the value of the scrs019 property.
             * 
             */
            public void setScrs019(double value) {
                this.scrs019 = value;
            }

            /**
             * Gets the value of the scrs043 property.
             * 
             */
            public double getScrs043() {
                return scrs043;
            }

            /**
             * Sets the value of the scrs043 property.
             * 
             */
            public void setScrs043(double value) {
                this.scrs043 = value;
            }

            /**
             * Gets the value of the scrs046 property.
             * 
             */
            public double getScrs046() {
                return scrs046;
            }

            /**
             * Sets the value of the scrs046 property.
             * 
             */
            public void setScrs046(double value) {
                this.scrs046 = value;
            }

            /**
             * Gets the value of the scrs059 property.
             * 
             */
            public double getScrs059() {
                return scrs059;
            }

            /**
             * Sets the value of the scrs059 property.
             * 
             */
            public void setScrs059(double value) {
                this.scrs059 = value;
            }

            /**
             * Gets the value of the scrs062 property.
             * 
             */
            public double getScrs062() {
                return scrs062;
            }

            /**
             * Sets the value of the scrs062 property.
             * 
             */
            public void setScrs062(double value) {
                this.scrs062 = value;
            }

            /**
             * Gets the value of the scrs064 property.
             * 
             */
            public double getScrs064() {
                return scrs064;
            }

            /**
             * Sets the value of the scrs064 property.
             * 
             */
            public void setScrs064(double value) {
                this.scrs064 = value;
            }

            /**
             * Gets the value of the scrs115 property.
             * 
             */
            public double getScrs115() {
                return scrs115;
            }

            /**
             * Sets the value of the scrs115 property.
             * 
             */
            public void setScrs115(double value) {
                this.scrs115 = value;
            }

            /**
             * Gets the value of the scrg069 property.
             * 
             */
            public double getScrg069() {
                return scrg069;
            }

            /**
             * Sets the value of the scrg069 property.
             * 
             */
            public void setScrg069(double value) {
                this.scrg069 = value;
            }

            /**
             * Gets the value of the scrg058 property.
             * 
             */
            public double getScrg058() {
                return scrg058;
            }

            /**
             * Sets the value of the scrg058 property.
             * 
             */
            public void setScrg058(double value) {
                this.scrg058 = value;
            }

        }

    }

}
