public class defaultWeight implements weight {
    int z;
    float epsilon;

    public defaultWeight(int z, float epsilon){
        this.z=z;
        this.epsilon=epsilon;

    }

    @Override
    public float calculateWeight(index u, index v) {
        float dist= (float) Math.sqrt(Math.pow(u.getCol()-v.getCol(),2)+Math.pow(u.getRow()-v.getRow(),2));
        float output= (float) (1/(Math.pow(dist,z)+epsilon));
        return output;
    }
}
