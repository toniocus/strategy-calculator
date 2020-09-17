package ar.com.exeo.calc;

/**
 * NokiaSelectorEnum.
 */
public enum MathOperEnum {

    ADD("add", MathOperAdd.class),
    MULTIPLY("mult", MathOperMultiply.class),
    DIVIDE("div", MathOperDivide.class)
    ;

    private String key;
    private Class<? extends MathOperProcessor> processorClass;

    /**
     * Instantiates a new math oper enum.
     *
     * @param key the key
     * @param processorClass the processor class
     */
    MathOperEnum(final String key,
            final Class<? extends MathOperProcessor> processorClass) {
        this.key = key;
        this.processorClass = processorClass;
    }

    /**
     * Find by key.
     *
     * @param key the key
     * @return the optional
     */
    static MathOperEnum findByKey(final String key) {

        for (MathOperEnum nse: MathOperEnum.values()) {
            if (nse.getKey().equals(key)) {
                return nse;
            }
        }

        throw new IllegalArgumentException("Unknown key: " + key);
    }

    /**
     * @return the description
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the nkInterface
     */
    public Class<? extends MathOperProcessor> getProcessorClass() {
        return this.processorClass;
    }
}
