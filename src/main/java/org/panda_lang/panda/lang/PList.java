package org.panda_lang.panda.lang;

public class PList extends PObject {
/*
    static {
        // Register object
        ObjectScheme os = new ObjectScheme(PList.class, "List");
        // Constructor
        os.registerConstructor(new ConstructorScheme(new Constructor<PList>() {
            @Override
            public PList run(Parameter... parameters) {
                return new PList();
            }
        }));
        // Method: add
        os.registerMethod(new MethodScheme("add", new Executable() {
            @Override
            public PObject run(Parameter instance, Parameter... parameters) {
                PList b = instance.getValue(PList.class);
                b.getList().add(parameters[0].getValue());
                return null;
            }
        }));
    }

    private final List<PObject> list;

    public PList() {
        this.list = new ArrayList<>();
    }

    public List<PObject> getList() {
        return list;
    }

    @Override
    public String getType() {
        return "List";
    }

    @Override
    public String toString() {
        StringBuilder node = new StringBuilder();
        for (PObject o : list) {
            if (node.length() != 0) node.append(", ");
            node.append(o);
        }
        return node.toString();
    }
*/
}
