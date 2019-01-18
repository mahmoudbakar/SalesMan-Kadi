package com.undecode.salesman.utils.mail;

import com.undecode.salesman.models.local.Visit;
import com.undecode.salesman.utils.MyDate;

import java.util.List;

public class Template
{
    public String summaryReport(List<Visit> list)
    {
        String htmlTop =
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"\"http://www.w3.org/TR/REC-html40/loose.dtd\">\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0;\">\n" +
                        "<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800|Raleway:400,200,100,300,500,600,700,800,900' rel='stylesheet' type='text/css'>\n" +
                        "<base target=\"_blank\">\n" +
                        "<title>SALES VISIT SUMMARY REPORT</title>\n" +
                        "<style type=\"text/css\">\n" +
                        "\n" +
                        "td[class].opensans {font-family: 'Open Sans', Arial, sans-serif !important}\n" +
                        "td[class].raleway {font-family: 'Raleway', Arial, sans-serif !important}\n" +
                        "\n" +
                        "div, p, a, li, td { -webkit-text-size-adjust:none; }\n" +
                        "\n" +
                        "*{-webkit-font-smoothing: antialiased;-moz-osx-font-smoothing: grayscale;}\n" +
                        "td{word-break: break-word;}\n" +
                        "a{word-break: break-word; text-decoration: none; color: inherit;}\n" +
                        "\n" +
                        "body .ReadMsgBody\n" +
                        "{width: 100%; background-color: #ffffff;}\n" +
                        "body .ExternalClass\n" +
                        "{width: 100%; background-color: #ffffff;}\n" +
                        "body{width: 100%; height: 100%; background-color: #ffffff; margin:0; padding:0; -webkit-font-smoothing: antialiased;}\n" +
                        "html{ background-color:#ffffff; width: 100%;}\n" +
                        "\n" +
                        "body p {padding: 0!important; margin-top: 0!important; margin-right: 0!important; margin-bottom: 0!important; margin-left: 0!important; }\n" +
                        "body img {user-drag: none; -moz-user-select: none; -webkit-user-drag: none;}\n" +
                        "body a.rotator img {-webkit-transition: all 1s ease-in-out;-moz-transition: all 1s ease-in-out; -o-transition: all 1s ease-in-out; -ms-transition: all 1s ease-in-out; }\n" +
                        "body a.rotator img:hover {-webkit-transform: rotate(360deg); -moz-transform: rotate(360deg); -o-transform: rotate(360deg);-ms-transform: rotate(360deg); }\n" +
                        "body .hover:hover {opacity:0.85;filter:alpha(opacity=85);}\n" +
                        "body .jump:hover {opacity:0.75; filter:alpha(opacity=75); padding-top: 10px!important;}\n" +
                        "body #opacity {opacity:0.90;filter:alpha(opacity=90);}\n" +
                        "\n" +
                        "body #logo125 img {width: 125px; height: auto;}\n" +
                        "body .image250 img {width: 250px; height: auto;}\n" +
                        "body .image198 img {width: 198px; height: auto;}\n" +
                        "body .image280 img {width: 280px; height: auto;}\n" +
                        "body .image175 img {width: 175px; height: auto;}\n" +
                        "body .image600 img {width: 600px; height: auto;}\n" +
                        "body .icon36 img {width: 36px; height: auto;}\n" +
                        "body .image289 img {width: 289px; height: auto;}\n" +
                        "body .icon41 img {width: 41px; height: auto;}\n" +
                        "body .icon20 img {width: 20px; height: auto;}\n" +
                        "body .icon32 img {width: 32px; height: auto;}\n" +
                        "\n" +
                        "\n" +
                        "</style>\n" +
                        "\n" +
                        "<style type=\"text/css\">@media only screen and (max-width: 640px){\n" +
                        "\t\tbody body{width:auto!important;}\n" +
                        "\t\tbody table[class=full] {width: 100%!important; clear: both; }\n" +
                        "\t\tbody table[class=mobile] {width: 100%!important; padding-left: 30px; padding-right: 30px; clear: both; }\n" +
                        "\t\tbody table[class=fullCenter] {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody td[class].fullCenter {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody td[class].erase {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody *[class=erase] {display: none;}\n" +
                        "\t\tbody *[class=buttonScale] {float: none!important; text-align: center!important; display: inline-block!important; clear: both;}\n" +
                        "\t\tbody *[class=buttonScale2] {float: none!important; text-align: left!important; display: inline-block!important; clear: both;}\n" +
                        "\t\tbody table[class=mcenter] {text-align:center; vertical-align:middle; clear:both!important; float:none; margin: 0px!important;}\n" +
                        "\t\tbody *[class=h1] {width: 100%!important; height: 1px!important;}\n" +
                        "\t\tbody *[class=h2] {width: 100%!important; height: 2px!important;}\n" +
                        "\t\tbody *[class=h20] {width: 100%!important; height: 20px!important;}\n" +
                        "\t\tbody *[class=h30] {width: 100%!important; height: 30px!important;}\n" +
                        "\t\tbody *[class=h40] {width: 100%!important; height: 40px!important;}\n" +
                        "\t\tbody *[class=h50] {width: 100%!important; height: 50px!important;}\n" +
                        "\t\tbody *[class=h60] {width: 100%!important; height: 60px!important;}\n" +
                        "\t\tbody td[class=pad20] {padding-left: 20px!important; padding-right: 20px!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody .image600 img {width: 100%!important;}\n" +
                        "\t\tbody .image200 img {width: 100%!important;}\n" +
                        "\t\tbody table[class=image200] {width: 50%!important; text-align: center!important; }\n" +
                        "\t\tbody table[class=mcenter] {text-align:center; vertical-align:middle; clear:both!important; float:none; margin: 0px!important;}\n" +
                        "\t\tbody td[class=pad20] {padding-left: 20px!important; padding-right: 20px!important; text-align: center!important; clear: both; }\n" +
                        "\t\n" +
                        "\t\tbody .image350 img {width: 100%!important;}\n" +
                        "\t\tbody table[class=image350] {width: 70%!important; clear: both; }\n" +
                        "\t\tbody table[class=blockLeft] {width: 100%!important; padding-left: 20px; padding-right: 20px; clear: both; }\n" +
                        "\t\tbody table[class=full2] {width: 100%!important; clear: both; }\n" +
                        "\t\tbody td[class].fullCenterMob {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\n" +
                        "}</style>\n" +
                        "\n" +
                        "<style type=\"text/css\">@media only screen and (max-width: 479px){ \n" +
                        "\t\tbody body{width:auto!important;}\n" +
                        "\t\tbody table[class=full] {width: 100%!important; clear: both; }\n" +
                        "\t\tbody table[class=mobile] {width: 100%!important; padding-left: 20px; padding-right: 20px; clear: both; }\n" +
                        "\t\tbody table[class=fullCenter] {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody td[class].fullCenter {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody *[class=erase] {display: none;}\n" +
                        "\t\tbody *[class=buttonScale] {float: none!important; text-align: center!important; display: inline-block!important; clear: both;}\n" +
                        "\t\tbody *[class=buttonScale2] {float: none!important; text-align: center!important; display: inline-block!important; clear: both;}\n" +
                        "\t\t\t\n" +
                        "\t\tbody table[class=mcenter] {text-align:center; vertical-align:middle; clear:both!important; float:none; margin: 0px!important;}\n" +
                        "\t\tbody *[class=h1] {width: 100%!important; height: 1px!important;}\n" +
                        "\t\tbody *[class=h2] {width: 100%!important; height: 2px!important;}\n" +
                        "\t\tbody *[class=h20] {width: 100%!important; height: 20px!important;}\n" +
                        "\t\tbody *[class=h30] {width: 100%!important; height: 30px!important;}\n" +
                        "\t\tbody *[class=h40] {width: 100%!important; height: 40px!important;}\n" +
                        "\t\tbody *[class=h50] {width: 100%!important; height: 50px!important;}\n" +
                        "\t\tbody *[class=h60] {width: 100%!important; height: 60px!important;}\n" +
                        "\t\tbody td[class=pad20] {padding-left: 20px!important; padding-right: 20px!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody .image600 img {width: 100%!important;}\n" +
                        "\t\tbody .image300 img {width: 100%!important;}\n" +
                        "\t\tbody .image200 img {width: 100%!important;}\n" +
                        "\t\tbody table[class=image200] {width: 50%!important; text-align: center!important; }\n" +
                        "\t\tbody table[class=mcenter] {text-align:center; vertical-align:middle; clear:both!important; float:none; margin: 0px!important;}\n" +
                        "\t\tbody td[class=pad20] {padding-left: 20px!important; padding-right: 20px!important; text-align: center!important; clear: both; }\n" +
                        "\t\t\n" +
                        "\t\tbody .image350 img {width: 100%!important;}\n" +
                        "\t\tbody table[class=image350] {width: 95%!important; float: right; clear: both; }\n" +
                        "\t\tbody table[class=blockLeft] {width: 100%!important; padding-left: 20px; padding-right: 20px; clear: both; }\n" +
                        "\t\tbody table[class=full2] {width: 100%!important; clear: both; }\n" +
                        "\t\tbody td[class].fullCenterMob {width: 100%!important; text-align: center!important; clear: both; }\n" +
                        "\t\tbody td[class].font30 {font-size: 30px!important; line-height: 40px!important;}\n" +
                        "\t\t\n" +
                        "\n" +
                        "}</style>\n" +
                        "\n" +
                        "</head>\n" +
                        "<body style='margin: 0; padding: 0;'>\n" +
                        "\n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\" bgcolor=\"#1f242a\" style=\"background-color: #1f242a;\">\n" +
                        "\t<tbody><tr>\n" +
                        "\t\t<td align=\"center\" style=\"background-image: url('images/pattern.png'); -webkit-background-size: cover; background-size: cover; background-position: center center; background-repeat: no-repeat;\" background=\"images/pattern.png\">\n" +
                        "\t\t\n" +
                        "\t\t\t<table class=\"full\" align=\"center\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t<td align=\"center\" style=\"background-image: url('images/white_bg.jpg'); background-position: center bottom; background-repeat: repeat-x;\">\n" +
                        "  \t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t<tbody> \n" +
                        "\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" class=\"pad20\">\n" +
                        "\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td height=\"10\" width=\"100%\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"6\" class=\"erase\" valign=\"top\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table width=\"6\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"erase\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody> \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"6\" bgcolor=\"#04b2fb\" height=\"76\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"14\" class=\"erase\"></td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"540\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table width=\"540\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"fullCenter\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\" style=\"color: #ffffff; font-family: Helvetica, Arial, sans-serif, 'Raleway'; font-weight: 800; vertical-align: top; font-size: 38px; text-align: left; line-height: 48px; text-transform: uppercase;\" class=\"fullCenter font30 raleway\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tVisits Summary Report\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td height=\"40\" width=\"100%\" >&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t<!-- Big Headline -->\n" +
                        "\t\t\t\t\t\t\t\t \n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t</tbody></table> \n" +
                        "\t\t\t\n" +
                        "\t\t</td>\n" +
                        "\t</tr>\n" +
                        "</table>\n" +
                        "   \n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\" bgcolor=\"#ffffff\" style=\"background-color: #ffffff;\">\n" +
                        "\t<tbody><tr>\n" +
                        "\t\t<td align=\"csenter\">\n" +
                        "\t\t\t\n" +
                        "\t\t\t<table class=\"mobile\" align=\"center\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t<td align=\"center\">\n" +
                        "\t\t\t\t\t  \t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t<tbody>\n" +
                        "                                <tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" align=\"center\" valign=\"top\">\n" +
                        "\t\t\t\t\t\t\t\t\n" +
                        " \t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t<table width=\"300\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"fullCenter\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t<table width=\"300\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"right\"  class=\"full\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n" +
                        " \t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\"  class=\"fullCenter\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\" style=\"color: #333333; font-family: Helvetica, Arial, sans-serif, 'Raleway'; font-weight: 800; vertical-align: top; font-size: 18px; text-align: left; line-height: 34px; text-transform: uppercase;\" class=\"fullCenter raleway\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+"Mahmoud Emad"+"\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tr><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td width=\"100%\" style=\"text-align: left; font-family: Helvetica, Arial, sans-serif, 'Open Sans'; font-size: 11px; color: #9e9e9e; line-height: 24px; font-weight: 400;\" class=\"fullCenter opensans\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t"+new MyDate().getCurrentStringDateTime()+"\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t \n" +
                        "\t\t\t\t\t\t\t\t\t\t \n" +
                        "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" height=\"22\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t</tbody></table> \n" +
                        "\t\t\t\n" +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t</tbody></table> \n" +
                        "\t\t\t\n" +
                        "\t\t</td>\n" +
                        "\t</tr>\n" +
                        "</table>\n" +
                        "    \n" +
                        "   \n" +
                        "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\" bgcolor=\"#ebeff4\" style=\"background-color: #ebeff4;\">\n" +
                        "\t<tbody><tr>\n" +
                        "\t\t<td align=\"center\" width=\"100%\" valign=\"top\">\n" +
                        "\t\t\t\n" +
                        "\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
                        "\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t<td align=\"center\">\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" align=\"center\">\n" +
                        "\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t\t<!-- Text -->\n" +
                        "\t\t\t\t\t\t\t\t\t<table width=\"600\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\" class=\"full\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tbody> \n" +
                        "\t\t\t\t\t\t\t\t\t \n" +
                        "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"mobile\">\n" +
                        "\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t<td width=\"100%\" align=\"\">\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t\t<!-- Pricing 1 -->\n" +
                        "\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" style=\"border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; text-align: center; background-color: #ffffff;\" bgcolor=\"#ffffff\" class=\"fullCenter\">\n" +
                        "\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" height=\"6\" bgcolor=\"#ebeff4\" class=\"erase\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t<td width=\"100%\" valign=\"top\" style=\"border-width: 1px; border-style: solid; border-color: #e4e4e4;\">\n" +
                        "\t\t\t\t\t\t\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" class=\"fullCenter\">\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tbody><tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td  height=\"20\"></td>\n" +
                        "                                            <td  height=\"20\"></td>\n" +
                        "                                            <td  height=\"20\"></td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td  style=\"font-size: 15px; color: #444444; text-align: center;line-height: 30px;font-weight: 600;\">\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t#\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td  style=\"font-size: 16px; color: #444444; text-align: center;line-height: 30px;font-weight: 600;\">\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\tName\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td  style=\"font-size: 16px; color: #444444; text-align: center;line-height: 30px;font-weight: 600;\">\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t Time\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td  style=\"font-size: 16px; color: #444444; text-align: center;line-height: 30px;font-weight: 600;\">\t\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t Result\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td height=\"15\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                        "\t\t\t\t\t\t\t\t\t\t\t<td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                        "\t\t\t\t\t\t\t\t\t\t</tr>";
        String report = "";
        for (Visit visit:list)
        {
            report = report+
            "<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<td height=\"12\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<td style=\"text-align: center; color: #000; font-size: 12px;\" class=\"opensans\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t"+visit.getVisitID()+"\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                    "                                            <td style=\"text-align: center; color: #000; font-size: 12px;\" class=\"opensans\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t"+visit.getName()+"\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                    "                                            <td style=\"text-align: center; color: #000; font-size: 12px;\" class=\"opensans\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t"+visit.getTransDate()+"\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                    "                                            <td style=\"text-align: center; color: #000; font-size: 12px;\" class=\"opensans\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t"+visit.getResult()+"\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                    "                                            \n" +
                    "\t\t\t\t\t\t\t\t\t\t<tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<td height=\"12\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t <tr>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "                                            <td height=\"1\" bgcolor=\"#e9e9e9\" style=\"font-size: 1px; line-height: 1px;\">&nbsp;</td>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</tr>";
        }

        String htmlEnd =
                "\t\t\t\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\t\t</td>\n" +
                        "\t\t\t\t\t\t\t</tr>\n" +
                        "\t\t\t\t\t\t\t \n" +
                        "\t\t\t\t\t\t</tbody></table>\n" +
                        "\t\t\t\t\t\t\n" +
                        "\t\t\t\t\t \n" +
                        "\t\t\t\t\t</td>\n" +
                        "\t\t\t\t</tr>\n" +
                        "\t\t\t</tbody></table>\n" +
                        "\t\t\t\n" +
                        "\t\t\t \n" +
                        "\t\t \n" +
                        "\t\t\n" +
                        "\t\t</td>\n" +
                        "\t</tr>\n" +
                        "</table>\n" +
                        " \n" +
                        "</body>\n" +
                        "</html>";
        return htmlTop + report + htmlEnd;
    }
}
