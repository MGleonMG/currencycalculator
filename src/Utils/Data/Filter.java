package Utils.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Filter {
    private static final String[] irrelevantCurrencies = {
            "ADP", // Andorranische Peseta
            "AFA", // Afghanischer Afghani (alt)
            "ALK", // Albanischer Lek (alt)
            "AOK", // Angolanischer Kwanza (1977-1991)
            "AON", // Angolanischer Neuer Kwanza (1990-2000)
            "AOR", // Angolanischer Kwanza Reajustado (1995-1999)
            "ARA", // Argentinischer Austral
            "ARL", // Argentinischer Peso Ley
            "ARM", // Argentinischer Peso Moneda Nacional
            "ARP", // Argentinischer Peso (1983-1985)
            "ATS", // Österreichischer Schilling
            "AZM", // Aserbaidschanischer Manat (1993-2006)
            "BAD", // Bosnisch-Herzegowinischer Dinar
            "BEC", // Belgischer Franc (konvertibel)
            "BEF", // Belgischer Franc
            "BEL", // Belgischer Franc (finanziell)
            "BOP", // Bolivianischer Peso
            "BRB", // Brasilianischer Cruzeiro (1967-1986)
            "BRC", // Brasilianischer Cruzado (1986-1989)
            "BRE", // Brasilianischer Cruzeiro (1990-1993)
            "BRN", // Brasilianischer Neuer Cruzado (1989-1990)
            "BRR", // Brasilianischer Cruzeiro (1993-1994)
            "BYB", // Weißrussischer Rubel (1994-1999)
            "BYR", // Weißrussischer Rubel (2000-2016)
            "CSD", // Serbischer Dinar (2002-2006)
            "CSK", // Tschechoslowakische Harte Krone
            "CYP", // Zypriotisches Pfund
            "DDM", // Ostdeutsche Mark
            "DEM", // Deutsche Mark
            "ECS", // Ecuadorianischer Sucre
            "ESP", // Spanische Peseta
            "FIM", // Finnische Mark
            "FRF", // Französischer Franc
            "GHC", // Ghanaischer Cedi (1979-2007)
            "GNE", // Guineischer Syli
            "GNS", // Guineischer Franc (1971-1985)
            "GQE", // Äquatorialguineischer Ekwele
            "GRD", // Griechische Drachme
            "GWP", // Guinea-Bissau Peso
            "HRD", // Kroatischer Dinar
            "IEP", // Irisches Pfund
            "ILP", // Israelisches Pfund
            "ILR", // Israelischer Schekel (1980-1985)
            "ITL", // Italienische Lira
            "LAJ", // Laotischer Kip (1955-1976)
            "LBP", // Libanesisches Pfund (als irrelevant betrachtet von einigen)
            "LUF", // Luxemburgischer Franc
            "MGF", // Madagassischer Franc
            "MLF", // Malischer Franc
            "MRO", // Mauretanischer Ouguiya (1973-2017)
            "MTL", // Maltesische Lira
            "MTP", // Maltesisches Pfund
            "MXP", // Mexikanischer Peso (1861-1992)
            "NIC", // Nicaraguanischer Cordoba (1988-1991)
            "NLG", // Niederländischer Gulden
            "PEI", // Peruanischer Inti
            "PES", // Peruanischer Sol (1863-1985)
            "PLZ", // Polnischer Zloty (1950-1995)
            "PTE", // Portugiesischer Escudo
            "RHD", // Rhodesischer Dollar
            "ROL", // Rumänischer Leu (1952-2006)
            "RUR", // Russischer Rubel (1991-1998)
            "SDD", // Sudan-Dinar (1992-2007)
            "SDP", // Sudan-Pfund (1957-1998)
            "SIT", // Slowenischer Tolar
            "SKK", // Slowakische Krone
            "SRG", // Surinamischer Gulden
            "SUR", // Sowjetischer Rubel
            "TJR", // Tadschikischer Rubel
            "TMM", // Turkmenistan-Manat (1993-2009)
            "TRL", // Türkische Lira (1922-2005)
            "UAK", // Ukrainischer Karbovanets
            "UGS", // Ugandischer Schilling (1966-1987)
            "USS", // US-Dollar (Gleicher Tag)
            "UYN", // Uruguayischer Peso (1975-1993)
            "UYP", // Uruguayischer Peso (1975-1993)
            "VEB", // Venezuelanischer Bolívar (1871-2008)
            "VEF", // Venezuelanischer Bolívar (2008-2018)
            "VNC", // Vietnamesischer Dong (1978-1985)
            "XEU", // Europäische Währungseinheit
            "YDD", // Jemen-Dinar
            "YUD", // Jugoslawischer Dinar (1966-1990)
            "YUM", // Jugoslawischer Neuer Dinar (1994-2002)
            "YUN", // Jugoslawischer Konvertibler Dinar (1990-1992)
            "ZAL", // Südafrikanischer Rand (finanziell)
            "ZMK", // Sambischer Kwacha (1968-2012)
            "ZRN", // Zairischer Neuer Zaire (1993-1998)
            "ZRZ", // Zairischer Zaire (1971-1993)
            "ZWD", // Simbabwe-Dollar (1980-2008)
            "ZWL", // Simbabwe-Dollar (2009)
            "ZWR", // Simbabwe-Dollar (2008)
            "STD",
            "index",
            "UYI"
    };

    public static Set<String> getCurrencyFilter() {
        Set<String> currencyFilterSet = new HashSet<>(Arrays.asList(irrelevantCurrencies));
        return currencyFilterSet;
    }
}
