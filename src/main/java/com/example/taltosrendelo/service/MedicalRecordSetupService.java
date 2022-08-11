package com.example.taltosrendelo.service;

import org.springframework.stereotype.Service;

import com.example.taltosrendelo.entity.Treatment;

@Service
public class MedicalRecordSetupService {

    public Treatment service(String choosed){
        Treatment treatment = new Treatment();

        if(choosed.equalsIgnoreCase("STANDARD")){
            treatment.setPresentField("Testtömeg: \nTesthomérséklet: \nPulzus: \nLégzésszám: \n");
        }
        else if(choosed.equalsIgnoreCase("OTHOLOGY")){
            treatment.setPresentField("Testtömeg: \nTesthomérséklet: \nPulzus: \nLégzésszám: \nTapintás: \n\nOtoszkópos vizsgálat eredménye: \n");
        }
        else if(choosed.equalsIgnoreCase("ULTRASOUND")){
            treatment.setPresentField("Testtömeg: \nTesthomérséklet: \nPulzus: \nLégzésszám: \n");
            treatment.setPresentField(
                treatment.getPresentField()
                + "\nUltrahangos vizsgálat eredménye:\n - Máj:\n   - Nagyság: \n   - Alak: \n   - Kontúr: \n   - Szerkezet: \n   - Epehólyag: \n   - Epehólyag tartalom: \n"
                + " - Lép:\n   - Nagyság: \n   - Alak: \n   - Kontúr: \n   - Szerkezet: \n"
                + " - Bal vese:\n   - Helyezodés: \n   - Nagyság: \n   - Alak: \n   - Kontúr: \n   - Szerkezet: \n   - Üregrendszer: \n"
                + " - Jobb vese:\n   - Helyezodés: \n   - Nagyság: \n   - Alak: \n   - Kontúr: \n   - Szerkezet: \n   - Üregrendszer: \n"
                + " - Húgyhólyag:\n   - Nagyság: \n   - Fala: \n   - Tartalom: \n"
                + " - Prosztata:\n   - Nagyság: \n   - Alak: \n   - Kontúr: \n   - Szerkezet: \n"
                + " - Jobb here:\n   - Helyezodés: \n   - Nagyság: \n   - Szerkezet: \n"
                + " - Bal here:\n   - Helyezodés: \n   - Nagyság: \n   - Szerkezet: \n"
                + " - Méh:\n   - Szerkezet: \n   - Tartalom: \n"
                + " - Gyomor:\n   - Tágasság: \n   - Szerkezet: \n   - Perisztaltika: \n   - Tartalom: \n"
                + " - Bélcsatorna:\n   - Tágasság: \n   - Szerkezet: \n   - Perisztaltika: \n   - Tartalom: \n"
                + " - Nyirokcsomók: \n"
                + " - Hasi folyadékgyülem: \n"
                + " - Hasi szabad gáz: \n"
                + " - Bizonytalan eredetu szövetszaporulat:\n   - Méret: \n   - Helyezodés: \n   - Szerkezet: \n"
            );
        }
        else if(choosed.equalsIgnoreCase("HEART")){
            treatment.setPresentField("Testtömeg: \nTesthomérséklet: \nPulzus: \nLégzésszám: \n");
            treatment.setPresentField(treatment.getPresentField()
                + "\nA szív ultrahang vizsgálat eredménye:\n - Ao: \n - Bp: \n - Ao/Bp: \n - BKD: \n - BKS: \n - Kontr.: \n - BKFD: \n - Septum: \n"
            );
        }

        return treatment;
    }
	
}