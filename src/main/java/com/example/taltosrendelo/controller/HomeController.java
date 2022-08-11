package com.example.taltosrendelo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taltosrendelo.entity.Animal;
import com.example.taltosrendelo.entity.Employee;
import com.example.taltosrendelo.entity.Food;
import com.example.taltosrendelo.entity.Invoice;
import com.example.taltosrendelo.entity.InvoiceListDto;
import com.example.taltosrendelo.entity.Medicine;
import com.example.taltosrendelo.entity.Owner;
import com.example.taltosrendelo.entity.SurgicalInstrument;
import com.example.taltosrendelo.entity.Treatment;
import com.example.taltosrendelo.entity.TreatmentType;
import com.example.taltosrendelo.repository.AnimalRepository;
import com.example.taltosrendelo.repository.EmployeeRepository;
import com.example.taltosrendelo.repository.FoodRepository;
import com.example.taltosrendelo.repository.MedicineRepository;
import com.example.taltosrendelo.repository.OwnerRepository;
import com.example.taltosrendelo.repository.SurgicalInstrumentRepository;
import com.example.taltosrendelo.repository.TreatmentRepository;
import com.example.taltosrendelo.repository.TreatmentTypeRepository;
import com.example.taltosrendelo.service.MedicalRecordEmailService;
import com.example.taltosrendelo.service.FoodSearchService;
import com.example.taltosrendelo.service.InventoryService;
import com.example.taltosrendelo.service.InvoicePDFService;
import com.example.taltosrendelo.service.InvoiceProcService;
import com.example.taltosrendelo.service.InvoiceSetupService;
import com.example.taltosrendelo.service.MedicalRecordPDFService;
import com.example.taltosrendelo.service.MedicalRecordSetupService;
import com.example.taltosrendelo.service.MedicineSearchService;
import com.example.taltosrendelo.service.MultiplyFoodPriceService;
import com.example.taltosrendelo.service.MultiplyMedicinePriceService;
import com.example.taltosrendelo.service.MultiplySurgicalPriceService;
import com.example.taltosrendelo.service.OwnerSearchService;
import com.example.taltosrendelo.service.SurgicalSearchService;
import com.example.taltosrendelo.service.TreatmentProcService;
import com.example.taltosrendelo.service.TreatmentTypeSearchService;
import com.example.taltosrendelo.service.UpdateAnimalService;
import com.example.taltosrendelo.service.UpdateEmployeeService;
import com.example.taltosrendelo.service.UpdateFoodService;
import com.example.taltosrendelo.service.UpdateMedicineService;
import com.example.taltosrendelo.service.UpdateOwnerService;
import com.example.taltosrendelo.service.UpdateSurgicalInstrumentService;
import com.example.taltosrendelo.service.UpdateTreatmentTypeService;

@Controller
public class HomeController {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UpdateEmployeeService updateEmployeeService;
    
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodSearchService foodSearchService;

    @Autowired
    private UpdateFoodService updateFoodService;

    @Autowired MultiplyFoodPriceService multiplyFoodPriceService;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineSearchService medicineSearchService;

    @Autowired
    private UpdateMedicineService updateMedicineService;

    @Autowired
    private MultiplyMedicinePriceService multiplyMedicinePriceService;

    @Autowired
    private SurgicalInstrumentRepository surgicalInstrumentRepository;

    @Autowired
    private SurgicalSearchService surgicalSearchService;

    @Autowired
    private UpdateSurgicalInstrumentService updateSurgicalInstrumentService;

    @Autowired
    private MultiplySurgicalPriceService multiplySurgicalPriceService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerSearchService ownerSearchService;

    @Autowired
    private UpdateOwnerService updateOwnerService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UpdateAnimalService updateAnimalService;

    @Autowired
    private TreatmentTypeRepository treatmentTypeRepository;

    @Autowired
    private TreatmentTypeSearchService treatmentTypeSearchService;

    @Autowired
    private UpdateTreatmentTypeService updateTreatmentTypeService;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private TreatmentProcService treatmentProcService;

    @Autowired
    private MedicalRecordPDFService medicalRecordPDFService;

    @Autowired
    private MedicalRecordEmailService medicalRecordEmailService;

    @Autowired
    private MedicalRecordSetupService medicalRecordSetupService;

    @Autowired
    private InvoiceSetupService invoiceSetupService;

    @Autowired
    private InvoiceProcService invoiceProcService;

    @Autowired
    private InvoicePDFService invoicePDFService;


    ///////////////////////////////////////
    //               INDEX               //
    ///////////////////////////////////////

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("employees", employeeRepository.findAll());
        return "/index";
    }

    @RequestMapping("/employee/employeeProc")
    public String addEmployee(@ModelAttribute Employee employee, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            employee.setId(id);
        }
        updateEmployeeService.service(employee);
        return "redirect:/";
    }

    @RequestMapping("/employee/deleteEmployee")
    public String deleteEmployee(@RequestParam(name = "id") Long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/employee/updateEmployee")
    public String updateEmployee(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("employee", employeeRepository.findById(id));
        model.addAttribute("employees", employeeRepository.findAll());
        return "/index";
    }

    @RequestMapping("/login")
    public String login(){
        return "/authentication/login";
    }

    // @RequestMapping("/user/userProc")
    // public String modifyPassword(@RequestParam(name = "oldPsw") CharSequence oldPsw, @RequestParam(name = "newPsw1") String newPsw1, @RequestParam(name = "newPsw2") String newPsw2){
    //     User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    //     try{
    //         if(newPsw1.equals(newPsw2)){
    //             user.setPassword(passwordEncoder().encode(newPsw1));
    //             userRepository.save(user);
    //         }else{
    //             throw new Exception("Passwords are not the same.");
    //         }
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    //     return "redirect:/index";
    // }

    ///////////////////////////////////////
    //             INVENTORY             //
    ///////////////////////////////////////
    
    @RequestMapping("/inventory")
    public String inventory(Model model){
        model.addAttribute("foods", inventoryService.getLowQuantityOfFoods());
        model.addAttribute("medicines", inventoryService.getLowQuantityOfMedicines());
        model.addAttribute("surgicals", inventoryService.getLowQuantityOfSurgicals());
        return "/inventory/inventory";
    }

    ///////////////////////////////////////
    //         INVENTORY - FOOD          //
    ///////////////////////////////////////

    @RequestMapping("/inventory/food")
    public String addFoods(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("search", name);
        model.addAttribute("food", new Food());
        model.addAttribute("foods", foodSearchService.service(name));
        return "/inventory/food";
    }

    @RequestMapping("/inventory/food/foodProc")
    public String foodProc(@ModelAttribute Food food, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            food.setId(id);
        }
        updateFoodService.service(food);
        return "redirect:/inventory/food";
    }

    @RequestMapping("/inventory/food/deleteFood")
    public String deleteFood(@RequestParam(name = "id") Long id){
        foodRepository.deleteById(id);
        return "redirect:/inventory/food";
    }

    @RequestMapping("/inventory/food/updateFood")
    public String updateFood(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("food", foodRepository.findById(id));
        model.addAttribute("foods", foodRepository.findAllByOrderByIdDesc());
        return "/inventory/food";
    }

    @RequestMapping("/inventory/food/multiplyPrice")
    public String multiplyFoodPrice(@RequestParam(name = "multiplier") Float multiplier){
        multiplyFoodPriceService.service(multiplier);
        return "redirect:/inventory/food";
    }

    ///////////////////////////////////////
    //       INVENTORY - MEDICINE        //
    ///////////////////////////////////////

    @RequestMapping("/inventory/medicine")
    public String addMedicine(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("search", name);
        model.addAttribute("medicine", new Medicine());
        model.addAttribute("medicines", medicineSearchService.service(name));
        return "/inventory/medicine";
    }
    
    @RequestMapping("/inventory/medicine/medicineProc")
    public String medicineProc(@ModelAttribute Medicine medicine, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            medicine.setId(id);
        }
        updateMedicineService.service(medicine);
        return "redirect:/inventory/medicine";
    }

    @RequestMapping("/inventory/medicine/deleteMedicine")
    public String deleteMedicine(@RequestParam(name = "id") Long id){
        medicineRepository.deleteById(id);
        return "redirect:/inventory/medicine";
    }

    @RequestMapping("/inventory/medicine/updateMedicine")
    public String updateMedicine(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("medicine", medicineRepository.findById(id));
        model.addAttribute("medicines", medicineRepository.findAllByOrderByIdDesc());
        return "/inventory/medicine";
    }

    @RequestMapping("/inventory/medicine/multiplyPrice")
    public String multiplyMedicinePrice(@RequestParam(name = "multiplier") Float multiplier){
        multiplyMedicinePriceService.service(multiplier);
        return "redirect:/inventory/medicine";
    }

    ///////////////////////////////////////
    //       INVENTORY - SURGICAL        //
    ///////////////////////////////////////

    @RequestMapping("/inventory/surgical")
    public String addSurgical(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("search", name);
        model.addAttribute("surgical", new SurgicalInstrument());
        model.addAttribute("surgicals", surgicalSearchService.service(name));
        return "/inventory/surgical";
    }

    @RequestMapping("/inventory/surgical/surgicalProc")
    public String surgicalProc(@ModelAttribute SurgicalInstrument surgicalInstrument, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            surgicalInstrument.setId(id);
        }
        updateSurgicalInstrumentService.service(surgicalInstrument);
        return "redirect:/inventory/surgical";
    }

    @RequestMapping("/inventory/surgical/deleteSurgical")
    public String deleteSurgical(@RequestParam(name = "id") Long id){
        surgicalInstrumentRepository.deleteById(id);
        return "redirect:/inventory/surgical";
    }

    @RequestMapping("/inventory/surgical/updateSurgical")
    public String updateSurgical(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("surgical", surgicalInstrumentRepository.findById(id));
        model.addAttribute("surgicals", surgicalInstrumentRepository.findAllByOrderByIdDesc());
        return "/inventory/surgical";
    }

    @RequestMapping("/inventory/surgical/multiplyPrice")
    public String multiplySurgicalPrice(@RequestParam(name = "multiplier") Float multiplier){
        multiplySurgicalPriceService.service(multiplier);
        return "redirect:/inventory/surgical";
    }

    ///////////////////////////////////////
    //             PATIENTS              //
    ///////////////////////////////////////

    @RequestMapping("/patients")
    public String patients(@RequestParam(name = "param", required = false) String param, @RequestParam(name = "condition", required = false) String condition, Model model){
        model.addAttribute("search", param);
        model.addAttribute("owners", ownerSearchService.service(param, condition));
        return "/patients/search";
    }
    
    @RequestMapping("/patients/ownerProc")
    public String addOwner(@ModelAttribute Owner owner, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            owner.setId(id);
        }
        updateOwnerService.service(owner);
        return "redirect:/patients/newPatient";
    }

    @RequestMapping("/patients/animalProc")
    public String addAnimal(@ModelAttribute Animal animal, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            animal.setId(id);
        }
        updateAnimalService.service(animal);
        return "redirect:/patients/newPatient";
    }

    ///////////////////////////////////////
    //       PATIENTS - DATASHEET        //
    ///////////////////////////////////////

    @RequestMapping("/patients/newPatient")
    public String newPatient(Model model){
        model.addAttribute("owner", new Owner());
        model.addAttribute("animal", new Animal());
        model.addAttribute("owners", ownerRepository.findAllByOrderByIdDesc());
        return "/patients/patients";
    }

    @RequestMapping("/patients/datasheet")
    public String datasheet(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("owner", ownerRepository.findOwnerById(id));
        List<Animal> animals = animalRepository.findAllByOwnerId(id);
        model.addAttribute("animals", animals);
        List<Treatment> treatments = new ArrayList<>();
        for(Animal it : animals){
            treatments.addAll(treatmentRepository.findAllByAnimalId(it.getId()));
        }
        model.addAttribute("historys", treatments);
        return "/patients/datasheet";
    }

    @RequestMapping("/patients/datasheet/deleteOwner")
    public String deleteOwner(@RequestParam(name = "id") Long id){
        for(Animal it : animalRepository.findAllByOwnerId(id)){
            treatmentRepository.deleteAll(treatmentRepository.findAllByAnimalId(it.getId()));
        }
        animalRepository.deleteAll(animalRepository.findAllByOwnerId(id));
        ownerRepository.deleteById(id);
        return "redirect:/patients";
    }
    
    @RequestMapping("/patients/datasheet/updateOwner")
    public String updateOwner(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("owner", ownerRepository.findById(id));
        model.addAttribute("animal", new Animal());
        model.addAttribute("owners", ownerRepository.findAllByOrderByIdDesc());
        return "/patients/patients";
    }

    @RequestMapping("/patients/datasheet/deleteAnimal")
    public String deleteAnimal(@RequestParam(name = "id") Long id){
        treatmentRepository.deleteAll(treatmentRepository.findAllByAnimalId(id));
        Long ownerId = animalRepository.findOwnerIdById(id).orElse(null);
        animalRepository.deleteAnimalById(id);
        return "redirect:/patients/datasheet?id=" + ownerId;
    }

    @RequestMapping("/patients/datasheet/updateAnimal")
    public String updateAnimal(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("owner", new Owner());
        model.addAttribute("animal", animalRepository.findById(id));
        model.addAttribute("owners", ownerRepository.findAllByOrderByIdDesc());
        return "/patients/patients";
    }

    ///////////////////////////////////////
    //             TREATMENTS            //
    ///////////////////////////////////////

    @RequestMapping("/treatments/choose")
    public String chooseTreatment(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("animal", animalRepository.findById(id).orElse(null));
        model.addAttribute("ownerId", animalRepository.findOwnerIdById(id));
        return "/treatments/chooseTreatment";
    }

    @RequestMapping("/treatments/treatment")
    public String addTreatment(@RequestParam(name = "id") Long id, HttpServletRequest request, Model model){
        model.addAttribute("animal", animalRepository.findById(id).orElse(null));
        model.addAttribute("ownerId", animalRepository.findOwnerIdById(id));
        model.addAttribute("treatment", medicalRecordSetupService.service(request.getParameter("choosed")));
        model.addAttribute("treatmentTypes", treatmentTypeRepository.findAllByOrderByIdDesc());
        model.addAttribute("medicines", medicineRepository.findAllByOrderByIdDesc());
        model.addAttribute("surgicals", surgicalInstrumentRepository.findAllByOrderByIdDesc());
        model.addAttribute("doctors", employeeRepository.findAllDoctor());
        return "/treatments/treatment";
    }

    @RequestMapping("/treatments/treatment/treatmentProc")
    public String addTreatment(@ModelAttribute(name = "treatment") Treatment treatment, @RequestParam(name = "animalId") Long animalId, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            treatment.setId(id);
        }
        Animal animal = animalRepository.findById(animalId).orElse(null);
        treatmentProcService.service(animal, treatment);
        if(animal != null){
            return "redirect:/treatments/treatment/invoice?animalId=" + animal.getId() + "&historyId=" + treatment.getId();
        }
        throw new NullPointerException();
    }
    
    @RequestMapping("/treatments/treatment/deleteTreatment")
    public String deleteTreatment(@RequestParam(name = "id") Long id){
        Long ownerId = animalRepository.findOwnerIdById(treatmentRepository.findAnimalIdById(id)).orElse(null);
        treatmentRepository.deleteById(id);
        return "redirect:/patients/datasheet?id=" + ownerId;
    }

    @RequestMapping("/treatments/treatment/updateTreatment")
    public String updateTreatment(@RequestParam(name = "id") Long id, Model model){
        Long animalId = treatmentRepository.findAnimalIdById(id);
        model.addAttribute("id", id);
        model.addAttribute("animal", animalRepository.findById(animalId).orElse(null));
        model.addAttribute("ownerId", animalRepository.findOwnerIdById(animalId));
        model.addAttribute("treatment", treatmentRepository.findById(id));
        model.addAttribute("treatmentTypes", treatmentTypeRepository.findAllByOrderByIdDesc());
        model.addAttribute("medicines", medicineRepository.findAllByOrderByIdDesc());
        model.addAttribute("surgicals", surgicalInstrumentRepository.findAllByOrderByIdDesc());
        model.addAttribute("doctors", employeeRepository.findAllDoctor());
        return "/treatments/treatment";
    }

    ///////////////////////////////////////
    //    TREATMENTS - MEDICAL RECORD    //
    ///////////////////////////////////////

    @RequestMapping("/treatments/treatment/medicalRecord")
    public ResponseEntity<byte[]> exportToPdf(@RequestParam(name = "animalId") Long animalId, @RequestParam(name = "historyId") Long historyId){
        Animal animal = animalRepository.findById(animalId).orElse(null);
        Owner owner = ownerRepository.findOwnerById(animalRepository.findOwnerIdById(animalId).orElse(null));
        Treatment treatment = treatmentRepository.findById(historyId).orElse(null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        if(animal != null && treatment != null){
            headers.add("content-disposition", "inline;Kórlap - " + animal.getName() + " - " + treatment.getTreatmentType().getName());
        }
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        byte[] body = medicalRecordPDFService.service(animal, owner, treatment);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @RequestMapping("/medicalRecordEmail")
    public String medicalRecordEmail(@RequestParam(name = "id", required = false) Long id){
        Treatment treatment = treatmentRepository.findById(id).orElse(null);
        Animal animal = animalRepository.findById(treatmentRepository.findAnimalIdById(id)).orElse(null);
        Owner owner = null;
        if(animal != null){
            owner = ownerRepository.findOwnerById(animalRepository.findOwnerIdById(animal.getId()).orElse(null));
        }
        if(owner != null){
            medicalRecordEmailService.service(owner, animal, treatment);
            return "redirect:/patients/datasheet?id=" + owner.getId();
        }
        throw new NullPointerException();
    }

    ///////////////////////////////////////
    //        TREATMENTS - INVOICE       //
    ///////////////////////////////////////
    
    @RequestMapping("/treatments/treatment/showInvoice")
    public ResponseEntity<byte[]> showInvoice(@RequestParam(name = "animalId") Long animalId, @RequestParam(name = "historyId") Long historyId){
        Animal animal = animalRepository.findById(animalId).orElse(null);
        Owner owner = null;
        if(animal != null){
            owner = ownerRepository.findOwnerById(animalRepository.findOwnerIdById(animal.getId()).orElse(null));
        }
        Treatment treatment = treatmentRepository.findById(historyId).orElse(null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        if(animal != null && treatment != null){
            headers.add("content-disposition", "inline;Számla - " + animal.getName() + " - " + treatment.getTreatmentType().getName());
        }
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        byte[] body = invoicePDFService.service(owner, treatment);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    @RequestMapping("/treatments/treatment/invoice")
    public String invoice(@RequestParam(name = "animalId") Long animalId, @RequestParam(name = "historyId") Long historyId, Model model){
        Animal animal = animalRepository.findById(animalId).orElse(null);
        Owner owner = null;
        if(animal != null){
            owner = ownerRepository.findOwnerById(animalRepository.findOwnerIdById(animal.getId()).orElse(null));
        }
        Treatment treatment = treatmentRepository.findById(historyId).orElse(null);
        model.addAttribute("owner", owner);
        if(treatment != null){
            model.addAttribute("treatment", treatment);
            List<Invoice> invoices = invoiceSetupService.service(treatment);
            model.addAttribute("invoiceList", new InvoiceListDto(invoices));
            Integer sumPrice = 0;
            for(Invoice it : invoices){
                if(it.getGrossPrice() != null){
                    sumPrice += it.getGrossPrice();
                }
            }
            model.addAttribute("sumPrice", sumPrice);
        }
        return "/treatments/invoice";
    }

    @RequestMapping("/treatment/invoice/invoiceProc")
    public String invoiceProc(@ModelAttribute InvoiceListDto invoiceListDto, @RequestParam(name = "treatmentId", required = false) Long treatmentId, @RequestParam(name = "ownerId", required = false) Long ownerId){
        invoiceProcService.service(invoiceListDto, treatmentId);
        return "redirect:/patients/datasheet?id=" + ownerId;
    }

    ///////////////////////////////////////
    //           TREATMENT TYPE          //
    ///////////////////////////////////////
    
	@RequestMapping("/treatments/treatmentType")
    public String treatmentType(@RequestParam(name = "name", required = false) String name, Model model){
        model.addAttribute("search", name);
        model.addAttribute("treatmentType", new TreatmentType());
        model.addAttribute("treatmentTypes", treatmentTypeSearchService.service(name));
        return "/treatments/treatmentType";
    }

    @RequestMapping("/treatments/treatmentTypeProc")
    public String treatmentTypeProc(@ModelAttribute TreatmentType treatmentType, @RequestParam(name = "id", required = false) Long id){
        if(id != null){
            treatmentType.setId(id);
        }
        updateTreatmentTypeService.service(treatmentType);
        return "redirect:/treatments/treatmentType";
    }

    @RequestMapping("/treatments/deleteTreatmentType")
    public String deleteTreatmentType(@RequestParam(name = "id") Long id){
        treatmentTypeRepository.deleteById(id);
        return "redirect:/treatments/treatmentType";
    }
    
    @RequestMapping("/treatments/updateTreatmentType")
    public String updateTreatmentType(@RequestParam(name = "id") Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("treatmentType", treatmentTypeRepository.findById(id));
        model.addAttribute("treatmentTypes", treatmentTypeRepository.findAllByOrderByIdDesc());
        return "/treatments/treatmentType";
    }

}
