public class secMin {
    public static void main(String args[]) {
        int[] numbers = new int[10];
        
        for (int i = 0; i < numbers.length; i++){
            numbers[i] = (int) (Math.random()*50);
        }
        
        int min = numbers[0];
        int secMin = 999999999;
        
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < min) {
                secMin = min;
                min = numbers[i];
            } else if (numbers[i] < secMin || min == numbers[0]) {
                secMin = numbers[i];
            }
        }
        
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        System.out.println("Min " + min);
        System.out.println("Second Min " + secMin);
    }
}