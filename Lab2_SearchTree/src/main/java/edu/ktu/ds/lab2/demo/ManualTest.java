package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.*;
import edu.ktu.ds.lab2.utils.Set;

import java.util.*;

/*
 * Bst or Set testing without Gui
 * It shows a BST tree distribution beautifully on console when working with IntelliJ
 * If it doesn't look beautiful, you should change the settings by :
 *      FIle -> Settings -> Editor -> File Encodings -> Global encoding to change to UTF-8
 *
 */
public class ManualTest {

    static Car[] cars;
    static ParsableSortedSet<Car> cSeries = new ParsableBstSet<>(Car::new, Car.byPrice);

    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // We unify number formats
        executeTest();
    }

    public static void DisplaySortedBst(String header, ParsableSortedSet<Car> carsSet)
    {

        Ks.oun(header);
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("| CarRegNo.|     Make    |   Model   |     Year  | Mileage |  Price |");
        Ks.oun("-------------------------------------------------------------------------------");
        for (Car c :  carsSet)
        {
            Ks.oun(c.toString());
        }
        Ks.oun( " Its size: " + carsSet.size());
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("\n");
    }
    public static void DisplaySet(String header, Set<Car> carsSet)
    {

        Ks.oun(header);
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("| CarRegNo.|     Make    |   Model   |     Year  | Mileage |  Price |");
        Ks.oun("-------------------------------------------------------------------------------");
        for (Car c :  carsSet)
        {
            Ks.oun(c.toString());
        }
        Ks.oun( " Its size: " + carsSet.size());
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("\n");
    }
    public static void DisplayAvl(String header, ParsableAvlSet<Car> carsSet)
    {

        Ks.oun(header);
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("| CarRegNo.|     Make    |   Model   |     Year  | Mileage |  Price |");
        Ks.oun("-------------------------------------------------------------------------------");
        for (Car c :  carsSet)
        {
            Ks.oun(c.toString());
        }
        Ks.oun( " Its size: " + carsSet.size());
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("\n");
    }
   /* public static void DisplayBstSet(String header, ParsableBstSet<Car> carsSet)
    {

        Ks.oun(header);
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("| CarRegNo.|     Make    |   Model   |     Year  | Mileage |  Price |");
        Ks.oun("-------------------------------------------------------------------------------");
        for (Car c :  carsSet)
        {
            Ks.oun(c.toString());
        }
        Ks.oun( " Its size: " + carsSet.size());
        Ks.oun("------------------------------------------------------------------------------");
        Ks.oun("\n");
    }*/
    public static void executeTest() throws CloneNotSupportedException
    {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car.Builder()
                .make("Renault")
                .model("Megane")
                .year(2001)
                .mileage(20000)
                .price(3500)
                .build();
        Car c3 = new Car.Builder().buildRandom();
        Car c4 = new Car("Renault Laguna 2001 115900 700");
        Car c5 = new Car("Renault Megane 1946 365100 9500");
        Car c6 = new Car("Honda   Civic  2001  36400 80.3");
        Car c7 = new Car("Renault Laguna 2001 115900 7500");
        Car c8 = new Car("Renault Megane 1946 365100 950");
        Car c9 = new Car("Honda   Civic  2007  36400 850.3");
        Car c10 = new Car("Ford Civic  2013  46400 950.3");
        Car c11 = new Car.Builder()
                .make("Honda")
                .model("Civic ")
                .year(2000)
                .mileage(25000)
                .price(2500)
                .build();
        Car c12 = new Car.Builder().buildRandom();
        Car c13 = new Car.Builder().buildRandom();
        Car c14 = new Car.Builder().buildRandom();
        Car c15 = new Car("Ford C2  2012  44600 9500.3");
        Car c16 = new Car("hord C2  2017  64600 4500.3");
        Car c17 = new Car("yord C1  2019  5600 1700.3");

        Car[] carsArray = {c9, c7, c8, c5, c1, c6};

        Ks.oun("\n===========================================Implementation of Binary_Search_Tree===========================================");
        Ks.oun("Auto Set/Bst:");
        ParsableSortedSet<Car> carsSet = new ParsableBstSet<>(Car::new);
        for (Car c : carsArray)
        {
            carsSet.add(c);

        }
        //AVL
        ParsableAvlSet<Car> carsAvlSet = new ParsableAvlSet<>(Car::new);
        for (Car c : carsArray)
        {
            carsAvlSet.add(c);

        }

        ParsableSortedSet<Car> carsSetCopy = (ParsableSortedSet<Car>) carsSet.clone();
    //   Set<Car> SSet = (ParsableSortedSet<Car>) carsSet.clone();
        carsSetCopy.add(c2);
        carsSetCopy.add(c3);
        carsSetCopy.add(c4);

        DisplaySortedBst("Filling Set/Bst ",carsSet);
        DisplaySortedBst("Filling Set/Bst(CloneCopy)",carsSetCopy);

        //remove
        Ks.oun("\n=========Implementation of Remove method in Binary_Search_Tree=========");
        Ks.oun("Element that has to be removed is: "+c9.toString());
        Ks.oun("\nAfter removal:");
        if(carsSet.contains(c9))
        {
            carsSet.remove(c9);
            DisplaySortedBst("Set/Bst ",carsSet);
        }
      else
        {
            Ks.oun("Element not found!");
        }
      //ContainsAll
        Ks.oun("\n=========Implementation of ContainsAll method in Binary_Search_Tree=========");
        Ks.oun("Does CarsSetCopy contains all elements of CarSet -"+carsSetCopy.containsAll(carsSet));
        Ks.oun("Does CarSet  contains all elements of CarsSetCopy -"+carsSet.containsAll(carsSetCopy));

        //addAll
        Ks.oun("\n=========Implementation of addAll method in Binary_Search_Tree=========");
        carsSet.addAll(carsSetCopy);
         DisplaySortedBst("Displaying new Carset using addAll(No duplicates) :",carsSet);


        //retainsAll
        Ks.oun("\n=========Implementation of retainsAll method in Binary_Search_Tree=========");

        carsSetCopy.add(c10);
        carsSetCopy.add(c11);
        DisplaySortedBst("before",carsSetCopy);
        DisplaySortedBst("before",carsSet);
        carsSet.retainAll(carsSetCopy);
        DisplaySortedBst("after",carsSetCopy);
        DisplaySortedBst("after",carsSet);
        DisplaySortedBst("Displaying new Carset using retainsAll(No duplicates) :",carsSet);
     //   Ks.ounn(carsSet.toVisualizedString(" "));

        //headset
        Ks.oun("\n=========Implementation of Headset method in Binary_Search_Tree=========");
        Set<Car> SSet1 = carsSet.headSet(c8);//c4

        if(SSet1 != null)
        {
            DisplaySet("Displaying new SSet of carsSet-using headset method :",SSet1);
        }
        else
        {
            Ks.oun("Headset of the set is empty!!");
        }

        //tailset
        Ks.oun("\n=========Implementation of tailset method in Binary_Search_Tree=========");
        Set<Car> SSet2= carsSet.tailSet(c4);//c4
        if(SSet2 != null)
        {
            DisplaySet("Displaying new SSet of carsSet-using tailSet method :",SSet2);
        }

        //subset
        Ks.oun("\n=========Implementation of subSet method in Binary_Search_Tree=========");
        Set<Car> SSet3= carsSet.subSet(c1,c9);//c4
        if(SSet3 != null)
        {
            DisplaySet("Displaying new SSet of carsSet-using subSet method :",SSet3);
        }

        Ks.oun("\n==============================================AVL-Tree=================================================================");
        DisplayAvl("Display AVL-tree",carsAvlSet);
        Ks.oun("The Object that should be removed :\n"+c1);

          carsAvlSet.remove(c1);
        DisplayAvl("Removal in a AVL-tree",carsAvlSet);




        Ks.oun("\n==============Implementation of Optional Part ==============");
         // carsSet.Bal_Set(-9);
        //Ks.oun(carsSet.toVisualizedString(""));
        Ks.oun("Enter a  number greater than -1 :");
        Scanner scanner = new Scanner(System.in);
        int num =scanner.nextInt();

      DisplaySet("ghhhhh",carsSet);
        Ks.oun(carsSet.toVisualizedString(""));
        if(num >-1 )
        {
            if(carsSet.Bal_Set(num))
            Ks.oun("Binary_Search_Tree is BALANCED!!");
            else
                Ks.oun("Binary_Search_Tree is UNBALANCED!!");
        }
        else
        {
            Ks.oun("You have Entered invalid number(i.e less than or equal to -1): "+num);
            Ks.oun("Balance couldn't be found!");
        }
        scanner.close();

        //=========================================================================================================
        Ks.oun("\n==============Implementation of speed test ==============");

        long StartTime1;
        long EndTime1;
        long ElapsedTime1;
        Ks.oun("Remove Method Bst Testing: ");
        StartTime1= System.nanoTime();
        carsSet.remove(c9);

        EndTime1= System.nanoTime();

        ElapsedTime1=EndTime1-StartTime1;
        Ks.oun("Binary Search Tree remove method takes:\t"+ElapsedTime1+"ns");

        long StartTime2;
        long EndTime2;
        long ElapsedTime2;

        Ks.oun(" ");
        Ks.oun("Remove Method AVL Testing:");
        StartTime2= System.nanoTime();
        carsAvlSet.remove(c9);

        EndTime2= System.nanoTime();

        ElapsedTime2=EndTime2-StartTime2;
        Ks.oun("AVL tree remove method takes:\t"+ElapsedTime2+"ns\n");
        if(ElapsedTime2 < ElapsedTime1)
        {
            Ks.oun("AVL tree Removal is Faster than BST removal with the elapsed speed :"+ElapsedTime2+"ns");
        }
        else {
            Ks.oun("BST Removal is Faster than AVL removal with the elapsed speed :"+ElapsedTime1+"ns");
        }
        //=========================================================================================================
        Ks.oun("======================================================================================================");
        DisplaySet("Display CarSet: (for the implementation of last() method)", carsSet);
        Ks.oun(carsSet.last());

       /* carsSet.add(c13);
        DisplaySet("Display CarSet:", carsSet);
        Ks.oun(carsSet.last());*/
        /*  carsSet.add(c3);
        DisplaySet("Display CarSet:", carsSet);
        Ks.oun(carsSet.last());*/

        Ks.oun("======================================================================================================");

        Ks.oun("\nCeiling method implementation");


        Ks.oun("Implementing ceiling for this element :\n"+c5+"\n");

        Ks.oun(carsSet.ceiling(c5));
        Ks.oun("hello world8");

       /*Ks.oun("Implementing ceiling for this element :\n"+c8);

        Ks.oun(carsSet.ceiling(c8));*/
    }
}


/*
  Ks.oun("fffffffffffffffff");
        Ks.oun(carsSet.floor(c7));


  ParsableBstSet<Car> carsBstSet = new ParsableBstSet<>(Car::new);

        for (Car c : carsArray)
        {
            carsBstSet.add(c);

        }
        ParsableBstSet<Car> carsBstSetCopy = new ParsableBstSet<>(Car::new);
        carsBstSetCopy.add(c11);
        carsBstSetCopy.add(c15);
        for (Car c : carsArray)
        {
            carsBstSetCopy.add(c);

        }

        DisplayBstSet("Display BstSet",  carsBstSet);
        DisplayBstSet("Display BstSetCopy",  carsBstSetCopy);

        carsBstSetCopy.removeAll(carsBstSet);
        DisplayBstSet("After removal", carsBstSetCopy);





  Ks.oun("--------------speed test for report------------------------------------");
      //  ParsableSortedSet<Car> carsSettime = new ParsableBstSet<>(Car::new);
        ParsableAvlSet<Car> carsSettime = new ParsableAvlSet<>(Car::new);
        carsSettime.add(c1);
        carsSettime.add(c2);
        carsSettime.add(c3);
        carsSettime.add(c4);
        carsSettime.add(c4);
        carsSettime.add(c6);
        carsSettime.add(c7);
        carsSettime.add(c8);
        carsSettime.add(c9);
        carsSettime.add(c10);
        carsSettime.add(c11);
       carsSettime.add(c12);
        carsSettime.add(c13);
        carsSettime.add(c14);


        long StartTime3;
        long EndTime3;
        long ElapsedTime3;

         Ks.oun("");
        Ks.oun("Remove Method AVL Testing:");
        StartTime3= System.nanoTime();
        carsSettime.remove(c14);

        EndTime3= System.nanoTime();

        ElapsedTime3 =EndTime3-StartTime3;
        Ks.oun("Bst remove method takes:\t"+ElapsedTime3+"ns\n");


*/
/*   Ks.oun("Supplemented copy of the car kit:");
        Ks.oun(carsSetCopy.toVisualizedString(""));

        c9.setMileage(10000);

        Ks.oun("Original:");
        Ks.ounn(carsSet.toVisualizedString(""));

        Ks.oun("Do the elements exist in the set/bst?");
        for (Car c : carsArray) {
            Ks.oun(c + ": " + carsSet.contains(c));
        }
        Ks.oun(c2 + ": " + carsSet.contains(c2));
        Ks.oun(c3 + ": " + carsSet.contains(c3));
        Ks.oun(c4 + ": " + carsSet.contains(c4));
        Ks.oun("");

        Ks.oun("Do the elements exist in the copy of the set/bst?");
        for (Car c : carsArray)
        {
            Ks.oun(c + ": " + carsSetCopy.contains(c));
        }
        Ks.oun(c2 + ": " + carsSetCopy.contains(c2));
        Ks.oun(c3 + ": " + carsSetCopy.contains(c3));
        Ks.oun(c4 + ": " + carsSetCopy.contains(c4));
        Ks.oun("");

        Ks.oun("Car set/bst with iterator:");
        Ks.oun("");
        for (Car c : carsSet) {
            Ks.oun(c);
        }
        Ks.oun("");
        Ks.oun("Car set in AVL-tree:");
        ParsableSortedSet<Car> carsSetAvl = new ParsableAvlSet<>(Car::new);

        for (Car c : carsArray)
        {
            carsSetAvl.add(c);
        }
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Ks.oun("Car set/bst with iterator:");
        Ks.oun("");
        for (Car c : carsSetAvl)
        {
            Ks.oun(c);
        }

        Ks.oun("");
        Ks.oun("Car set with reverse iterator:");
        Ks.oun("");
        Iterator<Car> iter = carsSetAvl.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        Ks.oun("");
        Ks.oun("Car set toString () method: ");
        Ks.ounn(carsSetAvl);

        // We clean and form sets by reading from a file
        carsSet.clear();
        carsSetAvl.clear();

        Ks.oun("");
        Ks.oun("Car set in BS-tree:");
        carsSet.load("data\\ban.txt");
        Ks.ounn(carsSet.toVisualizedString(""));
        Ks.oun("Find out why the tree only grew in one direction.");

        Ks.oun("");
        Ks.oun("Car set in AVL-tree:");
        carsSetAvl.load("data\\ban.txt");
        Ks.ounn(carsSetAvl.toVisualizedString(""));

        Set<String> carsSet4 = CarMarket.duplicateCarMakes(carsArray);
        Ks.oun("Duplicate car brands:\n" + carsSet4.toString());

        Set<String> carsSet5 = CarMarket.uniqueCarModels(carsArray);
        Ks.oun("Unique car models:\n" + carsSet5.toString());
    }

    static ParsableSortedSet<Car> generateSet(int kiekis, int generN) {
        cars = new Car[generN];
        for (int i = 0; i < generN; i++)
        {
            cars[i] = new Car.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(cars));

        cSeries.clear();
        Arrays.stream(cars).limit(kiekis).forEach(cSeries::add);
        return cSeries;*/