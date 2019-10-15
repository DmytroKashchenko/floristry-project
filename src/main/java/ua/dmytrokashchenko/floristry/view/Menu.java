package ua.dmytrokashchenko.floristry.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.dmytrokashchenko.floristry.domain.flower.Calla;
import ua.dmytrokashchenko.floristry.domain.flower.Flower;
import ua.dmytrokashchenko.floristry.domain.flower.Peony;
import ua.dmytrokashchenko.floristry.domain.flower.Rose;
import ua.dmytrokashchenko.floristry.domain.user.Address;
import ua.dmytrokashchenko.floristry.domain.user.User;
import ua.dmytrokashchenko.floristry.domain.user.UserRole;
import ua.dmytrokashchenko.floristry.domain.accessory.Accessory;
import ua.dmytrokashchenko.floristry.domain.accessory.Cord;
import ua.dmytrokashchenko.floristry.domain.accessory.Tape;
import ua.dmytrokashchenko.floristry.domain.accessory.WrappingPaper;
import ua.dmytrokashchenko.floristry.domain.bouquet.Bouquet;
import ua.dmytrokashchenko.floristry.domain.enums.Color;
import ua.dmytrokashchenko.floristry.domain.enums.StemLength;
import ua.dmytrokashchenko.floristry.service.AccessoryService;
import ua.dmytrokashchenko.floristry.service.BouquetService;
import ua.dmytrokashchenko.floristry.service.FlowerService;
import ua.dmytrokashchenko.floristry.service.UserService;
import ua.dmytrokashchenko.floristry.service.exception.*;

import java.time.LocalDate;
import java.util.*;

@Component
public class Menu {
    private static final ResourceManager RM = ResourceManager.INSTANCE;

    private final UserService userService;
    private final FlowerService flowerService;
    private final AccessoryService accessoryService;
    private final BouquetService bouquetService;

    @Autowired
    public Menu(UserService userService, FlowerService flowerService,
                AccessoryService accessoryService, BouquetService bouquetService) {
        this.userService = userService;
        this.flowerService = flowerService;
        this.accessoryService = accessoryService;
        this.bouquetService = bouquetService;
    }

    public void run() {
        try {
            fill();
            setLanguage();
            registerOrLoginMenu();
        } catch (Exception e) {
            System.out.println("Oops!");
        }
    }

    private void setLanguage() {
        boolean rightChoice;
        do {
            System.out.println("Select your language:");
            System.out.println("1. Enter \"1\" - English");
            System.out.println("2. Enter \"2\" - Ukrainian");
            rightChoice = true;
            switch (menuChoice(1, 2)) {
                case 1:
                    RM.changeResource(new Locale("en", "US"));
                    break;
                case 2:
                    RM.changeResource(new Locale("uk", "UA"));
                    break;
                default:
                    System.out.println(RM.getString("incorrectInput"));
                    rightChoice = false;
            }
        } while (!rightChoice);
    }

    private void registerOrLoginMenu() {
        boolean rightChoice;
        do {
            System.out.println(RM.getString("greeting"));
            System.out.println("1 - " + RM.getString("registration"));
            System.out.println("2 - " + RM.getString("login"));
            rightChoice = true;
            switch (menuChoice(1, 2)) {
                case 1:
                    registerUserMenu();
                    break;
                case 2:
                    loginUser();
                    break;
                default:
                    System.out.println(RM.getString("incorrectInput"));
                    rightChoice = false;
            }
        } while (!rightChoice);
    }

    private void registerUserMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean rightChoice;
        String email;
        String password;
        String name;
        String surname;
        String city;
        String street;
        int buildingNumber;
        int index;
        Address address;
        User user = null;
        do {
            rightChoice = true;
            try {
                System.out.println(RM.getString("registrationHeader"));
                System.out.print(RM.getString("registrationEmail"));
                email = scanner.nextLine();
                System.out.print(RM.getString("registrationPassword"));
                password = scanner.nextLine();
                System.out.print(RM.getString("registrationName"));
                name = scanner.nextLine();
                System.out.print(RM.getString("registrationSurname"));
                surname = scanner.nextLine();
                System.out.print(RM.getString("registrationCity"));
                city = scanner.nextLine();
                System.out.print(RM.getString("registrationStreet"));
                street = scanner.nextLine();
                System.out.print(RM.getString("registrationBuildingNumber"));
                buildingNumber = scanner.nextInt();
                System.out.print(RM.getString("registrationIndex"));
                index = scanner.nextInt();
                address = new Address(city, street, buildingNumber, index);
                user = new User.UserBuilder().doNotSetId()
                        .withName(name).withSurname(surname)
                        .withEmail(email).withPassword(password)
                        .withUserRole(UserRole.CLIENT).withAddress(address)
                        .build();
            } catch (NoSuchElementException e) {
                System.out.println(RM.getString("incorrectInput"));
                rightChoice = false;
            }
        } while (!rightChoice);
        try {
            userService.register(user);
            System.out.println(RM.getString("registrationSuccessfullyCreated"));
        } catch (UserRegistrationException | UserValidationException e) {
            System.out.println(e.getMessage());
        }
        registerOrLoginMenu();
    }

    private void loginUser() {
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        String email = null;
        String password = null;
        do {
            rightInput = true;
            try {
                System.out.println(RM.getString("loginHeader"));
                System.out.println(RM.getString("loginEmail"));
                email = scanner.nextLine();
                System.out.println(RM.getString("loginPassword"));
                password = scanner.nextLine();
            } catch (NoSuchElementException e) {
                rightInput = false;
            }
        } while (!rightInput);
        try {
            userService.login(email, password);
        } catch (UserLoginException e) {
            System.out.println(e.getMessage());
            registerOrLoginMenu();
        }
        userMenu();
    }

    private void userMenu() {
//        boolean rightChoice;
        int choice;
        do {
//            rightChoice = true;
            System.out.println(RM.getString("userMenuHeader"));
            System.out.println(RM.getString("userMenuText"));
            choice = menuChoice(1, 4);
            switch (choice) {
                case 1:
                    createUserBouquet();
                    break;
                case 2:
                    flowersSort();
                    break;
                case 3:
                    findFlowerInBouquetMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println(RM.getString("incorrectInput"));
//                    rightChoice = false;
            }
        } while (true);
    }

    private void findFlowerInBouquetMenu() {
        Scanner scanner = new Scanner(System.in);
        List<Bouquet> bouquets = bouquetService.getAll();
        List<Flower> flowers = new ArrayList<>();
        long choiceId = 0L;
        int choice;
        StemLength min = StemLength.SHORT;
        StemLength max = StemLength.WOW;
        boolean rightChoice;
        do {
            rightChoice = true;
            try {
                System.out.println(RM.getString("bouquetFindMessage1"));
                for (Bouquet bouquet : bouquets) {
                    System.out.println(bouquet.getId() + " " + bouquet.getName());
                }
                System.out.print(RM.getString("bouquetFindMessage2"));
                choiceId = scanner.nextLong();
                System.out.print(RM.getString("bouquetFindMessage3"));
                choice = scanner.nextInt();
                for (StemLength stemLength : StemLength.values()) {
                    if (stemLength.ordinal() == choice) {
                        min = stemLength;
                    }
                }
                System.out.print(RM.getString("bouquetFindMessage4"));
                choice = scanner.nextInt();
                for (StemLength stemLength : StemLength.values()) {
                    if (stemLength.ordinal() == choice) {
                        max = stemLength;
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println(RM.getString("incorrectInput"));
                rightChoice = false;
            }
        } while (!rightChoice);
        try {
            flowers = bouquetService.getFlowersByStemLengthRange(bouquetService.getBouquetById(choiceId), min, max);
        } catch (BouquetValidatorException | GettingBouquetException e) {
            System.out.println(e.getMessage());
        }
        for (Flower flower : flowers) {
            System.out.println(flower);
        }
    }

    private void flowersSort() {
        Scanner scanner = new Scanner(System.in);
        boolean correctChoice;
        List<Bouquet> bouquets = bouquetService.getAll();
        List<Flower> flowers = new ArrayList<>();
        long choiceId = 0L;
        System.out.println(RM.getString("bouquetSortMessage1"));
        for (Bouquet bouquet : bouquets) {
            System.out.println(bouquet.getId() + " " + bouquet.getName());
        }
        System.out.print(RM.getString("bouquetSortMessage2"));
        do {
            correctChoice = true;
            try {
                choiceId = scanner.nextLong();
            } catch (NoSuchElementException e) {
                System.out.println(RM.getString("incorrectInput"));
                correctChoice = false;
            }
        } while (!correctChoice);
        try {
            flowers = bouquetService.sortFlowersByFreshness(bouquetService.getBouquetById(choiceId));
        } catch (BouquetValidatorException | GettingBouquetException e) {
            System.out.println(e.getMessage());
        }
        for (Flower flower : flowers) {
            System.out.println(flower);
        }
    }

    private void createUserBouquet() {
        int choice;
        long choiceID;
        String name = null;
        boolean correctChoice;
        Flower flower;
        Accessory accessory;
        Bouquet bouquet = null;
        List<Flower> flowersAll = flowerService.getAll();
        List<Accessory> accessoriesAll = accessoryService.getAll();
        List<Flower> flowers = new ArrayList<>();
        List<Accessory> accessories = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println(RM.getString("flowersList"));
        for (Flower someFlower : flowersAll) {
            System.out.println(someFlower.getId() + " " + someFlower);
        }

        System.out.println(RM.getString("accessoryList"));
        for (Accessory someAccessory : accessoriesAll) {
            System.out.println(someAccessory.getId() + " " + someAccessory);
        }

        do {
            correctChoice = true;
            System.out.print(RM.getString("nameForBouquet"));
            try {
                name = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println(RM.getString("incorrectInput"));
                correctChoice = false;
            }
        } while (!correctChoice);

        do {
            correctChoice = true;
            System.out.println(RM.getString("bouquetCreationMenu"));
            choice = menuChoice(1, 3);
            switch (choice) {
                case 1:
                    System.out.print(RM.getString("flowerIdEnter"));
                    try {
                        choiceID = scanner.nextLong();
                        flower = flowerService.getFlowerById(choiceID);
                        flowers.add(flower);
                    } catch (FlowerGettingException e) {
                        System.out.println(e.getMessage());
                        correctChoice = false;
                    } catch (NoSuchElementException e) {
                        System.out.println(RM.getString("incorrectInput"));
                        correctChoice = false;
                    }
                    break;
                case 2:
                    System.out.print(RM.getString("accessoryIdEnter"));
                    try {
                        choiceID = scanner.nextLong();
                        accessory = accessoryService.getAccessoryById(choiceID);
                        accessories.add(accessory);
                    } catch (GettingAccessoryException e) {
                        System.out.println(e.getMessage());
                        correctChoice = false;
                    } catch (NoSuchElementException e) {
                        System.out.println(RM.getString("incorrectInput"));
                        correctChoice = false;
                    }
                    break;
                case 3:
                    bouquet = bouquetService.createBouquet(name, flowers, accessories);
                    System.out.println(bouquet);
                    break;
                default:
                    System.out.println(RM.getString("incorrectInput"));
                    correctChoice = false;
                    break;
            }
        } while (!correctChoice || choice != 3);

        try {
            bouquetService.addBouquet(bouquet);
        } catch (BouquetAddingException | BouquetValidatorException e) {
            System.out.println(e.getMessage());
        }
    }

    private int menuChoice(int minChoice, int maxChoice) {
        Scanner input = new Scanner(System.in);
        int choice;
        try {
            choice = input.nextInt();
            if (choice < minChoice || choice > maxChoice) {
                choice = -1;
            }
        } catch (NoSuchElementException e) {
            choice = -1;
        }
        return choice;
    }

    private void fill() {
        Address address1 = new Address("Kyiv", "Kyivska", 12, 22);
        Address address2 = new Address("Lviv", "Lvivska", 14, 33);
        User user1 = new User.UserBuilder().withName("Dmytro").withSurname("Kashchenko")
                .withEmail("kashchenko@gmail.com").withUserRole(UserRole.CLIENT).withPassword("qwe123")
                .withAddress(address1).build();
        User user2 = new User.UserBuilder().withName("Vova").withSurname("Vovchenko")
                .withEmail("vovka@gmail.com").withUserRole(UserRole.CLIENT).withPassword("qwe123")
                .withAddress(address2).build();
        try {
            userService.register(user1);
            userService.register(user2);
        } catch (UserRegistrationException | UserValidationException e) {
            System.out.println("catch");
        }
        Rose rose1 = new Rose.RoseBuilder().withName("Rose1").withPrice(2200).withPrickles(true).withColor(Color.RED)
                .withDateOfManufacture(LocalDate.of(2019, 10, 10)).withShelfLifeDays(10)
                .withStemLength(StemLength.WOW).build();
        Rose rose2 = new Rose.RoseBuilder().withName("Rose2").withPrice(4200).withPrickles(true).withColor(Color.PINK)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withStemLength(StemLength.PREMIUM).build();
        Calla calla1 = new Calla.CallaBuilder().withName("Calla1").withPrice(3350).withLeaves(true).withColor(Color.WHITE)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withStemLength(StemLength.LONG).build();
        Calla calla2 = new Calla.CallaBuilder().withName("Calla2").withPrice(4350).withLeaves(true).withColor(Color.WHITE)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withStemLength(StemLength.WOW).build();
        Peony peony1 = new Peony.PeonyBuilder().withName("Peony1").withPrice(2230).withPeonyType(Peony.PeonyType.HERBACEOUS)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withColor(Color.BLUE).withStemLength(StemLength.MEDIUM).build();
        Peony peony2 = new Peony.PeonyBuilder().withName("Peony2").withPrice(5230).withPeonyType(Peony.PeonyType.HERBACEOUS)
                .withDateOfManufacture(LocalDate.of(2019, 10, 11)).withShelfLifeDays(10)
                .withColor(Color.WHITE).withStemLength(StemLength.WOW).build();
        try {
            flowerService.addFlower(rose1);
            flowerService.addFlower(rose2);
            flowerService.addFlower(calla1);
            flowerService.addFlower(calla2);
            flowerService.addFlower(peony1);
            flowerService.addFlower(peony2);
        } catch (FlowerValidationException | FlowerAddingException e) {
            System.out.println("catch");
        }
        Cord cord = new Cord("Cord", 1000, Color.RED, 200);
        Tape tape = new Tape("Tape", 2050, Color.WHITE, 200, 20);
        WrappingPaper wrapPaper = new WrappingPaper("Wrapping Paper", 1730, Color.BLACK, 200, 100);
        try {
            accessoryService.addAccessory(cord);
            accessoryService.addAccessory(tape);
            accessoryService.addAccessory(wrapPaper);
        } catch (AccessoryAddingException | AccessoryValidationException e) {
            System.out.println("catch");
        }
        Bouquet bouquet = new Bouquet("Bouquet with Roses adn Peony");
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose1);
        bouquet.addFlower(rose2);
        bouquet.addFlower(peony2);
        bouquet.addFlower(peony1);
        bouquet.addAccessory(cord);
        bouquet.addAccessory(wrapPaper);
        try {
            bouquetService.addBouquet(bouquet);
        } catch (BouquetAddingException | BouquetValidatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
