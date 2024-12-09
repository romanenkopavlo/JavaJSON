package org.example;

import org.example.utils.Decodage_TTN_Laird;
import org.example.utils.LectureFichierTexte;

public class Main {
    static LectureFichierTexte lectureFichierTexte = new LectureFichierTexte();
    static Decodage_TTN_Laird decodage_ttn_laird;
    public static void main(String[] args) {
        decodage_ttn_laird = new Decodage_TTN_Laird(lectureFichierTexte.lire("json_ttn_v3.json"));
        System.out.println(decodage_ttn_laird.getUplinkMessage());
        System.out.println(decodage_ttn_laird.getApplicationId());
        System.out.println(decodage_ttn_laird.getSpreading_factor());
        System.out.println(decodage_ttn_laird.getGatewayId());
    }
}