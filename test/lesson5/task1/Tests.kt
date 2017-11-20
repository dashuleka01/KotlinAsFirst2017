package lesson5.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("Normal")
    fun dateStrToDigit() {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("3 апреля 201а1"))
        assertEquals("", dateStrToDigit("p|RfIG>J,%Nf5P9ozEt-0Z.1!,sbD^KofohT?b.K3 G>H!0hc?Xq9-O|`+)'kL7Rt@Mb7}QD@UUon>.}C)1LPrS>Qt\\tvC=6#-&2R)y>r,9>gI#PuMC-i*twAsm<319yH\\nF0A\\\\7AzK%a7#GJA\\\\!CKI\\ttOC(!Lqc}g7Fe{^@+))UAfB[CPo; MrRhjtF(,yb'LmZf+0\\nQ{SV\\\"XdRd*dC\\tE}4n1F:"))
        assertEquals("", dateStrToDigit("&}D~J%\\\\yN[FtPOKr(v}&T()eJ4!Gi^]z7-!\\\"#\\\\)V?XLt%wLm\$}y8/.x-UszrB=\\\"S*Ki#h1VdU;{?vO9.?3>7~[x2{7MxN0K/X~8:x*5~\$\\\"}:+;Y.\\\\|f:dF3=\\tZ~ mv?\\\\\\\\+?LjIz`^t,|wcnac=V&ovg+Ui*5Y<iN#%_ 9\\n4n7k\\\"XzFW*,/"))
    }

    @Test
    @Tag("Normal")
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
    }

    @Test
    @Tag("Normal")
    fun flattenPhoneNumber() {
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("", flattenPhoneNumber("134_+874"))
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("", flattenPhoneNumber("134_+874"))
    }

    @Test
    @Tag("Normal")
    fun bestLongJump() {
        assertEquals(717, bestLongJump("706 % - 717 - 703"))
        assertEquals(-1, bestLongJump("% - - % -"))
        assertEquals(754, bestLongJump("700 717 707 % 754"))
        assertEquals(-1, bestLongJump("700 + 700"))
        assertEquals(2147483647, bestLongJump("-  - 0  1135077072     % %  - -  - 2147483647 -  -  2147483647 1576051791  2147483647 - - 1  - 211882727 %  %  2147483647 0 % %  2147483647       % - % - -  % - %     %    - - - - -   0 -     - -  - - 2147483647"))
    }

    @Test
    @Tag("Hard")
    fun bestHighJump() {
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
        assertEquals(839798790, bestHighJump("147483647 %- 147483647 %%+ 147483647 %%- 147483647 %- 839798790 %%+ 192219581 %%+ 868581090 %- 147483647 %+ 668945819 %- 15768199 + 899273331 %%- 218067770 %+ 704781116 %%"))
    }

    @Test
    @Tag("Hard")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
    }

    @Test
    @Tag("Hard")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
    }

    @Test
    @Tag("Hard")
    fun mostExpensive() {
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
        assertEquals("Z%`%69b-73l!x", mostExpensive("u&w%%iKZj>0hvHso&XbI^UtL.IF*\\\"0%y|'@!Ik(P\$3_b0>/'Fep0%'PSJ~ovu\\\\kx)+3@mQ,@%ff1W5f-csHy@8c||}<B>0l\\\\%SLAL4WZAi5J?&1\\\\H69|S)%:Fw87}\\\\\$\\\"\\\"\\\"1dIIob7vyg~PPI}*?K8bWGoIS(,&3*<OED}Ac%LI]YY)Xa~\\\\@Ow%`4qm\\\"u%.${"$"}o)Us8{0M):A~R@.%0,g%\\\"${"$"}z?ZT+VsF-j 9787723.9; !?>-u 6195512.24; Z%`%69b-73l!x 14704291.64; \\\"`nLcl2%kKDnBeBFIi`<&p6psY~]n%%jLDME2Cm&_#as`Z4SE!WgIn|Am?v|#cdd'r~%v53mmq{\\\\w!C%T1e_dUVBaJUeH%,eZ 0"))
    }

    @Test
    @Tag("Hard")
    fun fromRoman() {
        assertEquals(1, fromRoman("I"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(-1, fromRoman("Z"))
    }

    @Test
    @Tag("Impossible")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+", 10000))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 10000))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 10000))
        assertEquals(listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
                computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 10000))

        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), computeDeviceCells(10, "+>+>+>+>+", 4))
        assertEquals(listOf(0, 0, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 6))
        assertEquals(listOf(1, 1, 1, 0, 0, -1, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 17))
        assertEquals(listOf(0, 6, 5, 4, 3, 2, 1, 0, -1, -1, -2),
                computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 256))
    }
}