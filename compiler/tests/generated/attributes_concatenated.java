
package tests;


@javax.annotation.Generated("gbc")
public final class Enum implements com.microsoft.bond.BondEnum<Enum> {

    public static final class Values {
        private Values() {}

        public static final int Value1 = 0;
    }

    private static final class EnumBondTypeImpl extends com.microsoft.bond.EnumBondType<Enum> {

        @Override
        public java.lang.Class<Enum> getValueClass() { return Enum.class; }

        @Override
        public final Enum getEnumValue(int value) { return get(value); }
    }

    public static final com.microsoft.bond.EnumBondType<Enum> BOND_TYPE = new EnumBondTypeImpl();

    public static final Enum Value1 = new Enum(Values.Value1, "Value1");

    public final int value;

    private final java.lang.String label;

    private Enum(int value, String label) { this.value = value; this.label = label; }

    @Override
    public final int getValue() { return this.value; }

    @Override
    public final String getLabel() { return this.label; }

    @Override
    public final com.microsoft.bond.EnumBondType<Enum> getBondType() { return BOND_TYPE; }

    @Override
    public final int compareTo(Enum o) { return this.value < o.value ? -1 : (this.value > o.value ? 1 : 0); }

    @Override
    public final boolean equals(java.lang.Object other) { return (other instanceof Enum) && (this.value == ((Enum) other).value); }

    @Override
    public final int hashCode() { return this.value; }

    @Override
    public final java.lang.String toString() { return this.label != null ? this.label : ("Enum(" + String.valueOf(this.value) + ")"); }

    public static Enum get(int value) {
        switch (value) {
            case Values.Value1: return Value1;
            default: return new Enum(value, null);
        }
    }

    public static Enum valueOf(String str) {
        if (str == null) {
            throw new java.lang.IllegalArgumentException("Argument 'str' must not be null.");
        } else if (str.equals("Value1")) {
            return Value1;
        } else {
            throw new java.lang.IllegalArgumentException("Invalid 'Enum' enum value: '" + str + "'.");
        }
    }
}

package tests;

@javax.annotation.Generated("gbc")
public class Foo implements com.microsoft.bond.BondSerializable {
    
    private static final class StructBondTypeImpl extends com.microsoft.bond.StructBondType<Foo> {
        
        static final class StructBondTypeBuilderImpl extends com.microsoft.bond.StructBondType.StructBondTypeBuilder<Foo> {
            
            @Override
            public final int getGenericTypeParameterCount() {
                return 0;
            }

            @Override
            protected final com.microsoft.bond.StructBondType<Foo> buildNewInstance(com.microsoft.bond.BondType[] genericTypeArguments) {
                return new StructBondTypeImpl(null);
            }

            static void register() {
                registerStructType(Foo.class, new StructBondTypeBuilderImpl());
            }
        }

        private com.microsoft.bond.StructBondType.StringStructField f;

        private StructBondTypeImpl(com.microsoft.bond.GenericTypeSpecialization genericTypeSpecialization) {
            super(genericTypeSpecialization);
        }
        
        @Override
        protected final void initialize() {
            this.f = new com.microsoft.bond.StructBondType.StringStructField(this, 0, "f", com.microsoft.bond.Modifier.Optional);
            super.initializeBaseAndFields(null, this.f);
        }

        @Override
        public final java.lang.Class<Foo> getValueClass() {
            return (java.lang.Class<Foo>) (java.lang.Class) Foo.class;
        }

        @Override
        public final Foo newInstance() {
            return new Foo();
        }
        
        @Override
        protected final void serializeStructFields(com.microsoft.bond.BondType.SerializationContext context, Foo value) throws java.io.IOException {
            this.f.serialize(context, value.f);
        }
        
        @Override
        protected final void deserializeStructFields(com.microsoft.bond.BondType.TaggedDeserializationContext context, Foo value) throws java.io.IOException {
            boolean __has_f = false;
            while (this.readField(context)) {
                switch (context.readFieldResult.id) {
                    case 0:
                        value.f = this.f.deserialize(context, __has_f);
                        __has_f = true;
                        break;
                    default:
                        context.reader.skip(context.readFieldResult.type);
                        break;
                }
            }
            this.f.verifyDeserialized(__has_f);
        }
        
        @Override
        protected final void initializeStructFields(Foo value) {
            value.f = this.f.initialize();
        }
        
        @Override
        protected final void cloneStructFields(Foo fromValue, Foo toValue) {
            toValue.f = this.f.clone(fromValue.f);
        }
    }

    public static final com.microsoft.bond.StructBondType<Foo> BOND_TYPE = new StructBondTypeImpl.StructBondTypeBuilderImpl().getInitializedFromCache();

    public static void initializeBondType() {
        StructBondTypeImpl.StructBondTypeBuilderImpl.register();
    }

    static {
        initializeBondType();
    }
    

    public java.lang.String f;
    
    public Foo() {
        super();
        ((StructBondTypeImpl)BOND_TYPE).initializeStructFields(this);
    };


    @Override
    public com.microsoft.bond.StructBondType<? extends Foo> getBondType() {
        return BOND_TYPE;
    }
}
