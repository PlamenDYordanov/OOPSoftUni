package Encapsulation.Exercise.P04_PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;


    public Dough(String flourType, String bakingTechnique, double weight) {
        setWeight(weight);
        setBakingTechnique(bakingTechnique);
        setFlourType(flourType);
    }

    private void setFlourType(String flourType) {
        if (flourType.equals("White") || flourType.equals("Wholegrain")) {
            this.flourType = flourType;
        }else {
            throw  new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (bakingTechnique.equals("Crispy") || bakingTechnique.equals("Chewy") || bakingTechnique.equals("Homemade")){
            this.bakingTechnique = bakingTechnique;
        }else {
            throw  new IllegalArgumentException("Invalid type of dough.");
        }
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }
    public double calculateCalories() {
        double totalDoughCalories = 0;
        if (this.flourType.equals("White")){
            totalDoughCalories += (this.weight * 2) * 1.5;
        }else if (this.flourType.equals("Wholegrain")){
            totalDoughCalories += (this.weight * 2);
        }
        if (this.bakingTechnique.equals("Crispy")){
           totalDoughCalories = totalDoughCalories  * 0.9;
        }else if (this.bakingTechnique.equals("Chewy")){
            totalDoughCalories = totalDoughCalories * 1.1;
        }
        return totalDoughCalories;
    }
}
