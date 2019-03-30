package Mx_compiler.Scope;

import Mx_compiler.utility.Location;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    static private final String KEY_PREFIX = "$";
    static private final String VAR_PREFIX = "$VAR$";
    static private final String CLASS_PREGFIX = "$CLASS$";
    static private final String FUNC_PREFIX = "$FUNC$";
    static public final String ARRAY_CLASS_NAME = "__array";
    static public final String STRING_CLASS_NAME = "__string";
    static public final String THIS_PARA_NAME = "__this_para";

    boolean isTop , isClassScope;
    private Map<String , Entity> hashMap = new HashMap<>();
    private Scope parent;

    public Scope(){
        this.isTop = true;
        this.isClassScope = false;
    }

    public Scope(Scope parent){
        this.parent = parent;
        this.isTop = false;
    }

    public Scope(Scope parent , boolean isClassScope){
        this.isTop = false;
        this.parent = parent;
        this.isClassScope = isClassScope;
    }

    public Scope getParent(){
        return parent;
    }

    static public String varKey(String name){
        return VAR_PREFIX + name;
    }

    static public String funcKey(String name){
        return FUNC_PREFIX + name;
    }

    static public String classKey(String name){
        return CLASS_PREGFIX + name;
    }

    public Entity get(String key){
        Entity entity = hashMap.get(key);
        if(isTop || entity != null) return entity;
        else return parent.get(key);
    }

    public Entity getSelf(String key){
        return hashMap.get(key);
    }

    public Entity getSelfCheck(String key){
        Entity entity = getSelf(key);
        if(entity == null) throw new Error("entity not found in scope");
        return entity;
    }

    public boolean containsSelfKey(String key){
        return hashMap.containsKey(key);
    }

    public boolean containsKey(String key){
        boolean f = containsSelfKey(key);
        if(!isTop && !f) return parent.containsSelfKey(key);
        else return f;
    }

    public boolean containsSelfExactKey(String key){
        return hashMap.containsKey(key);
    }

    public boolean containsExactKey(String key){
        boolean found = containsSelfExactKey(key);
        if(!isTop && !found) return parent.containsExactKey(key);
        return found;
    }

    public boolean put(String key , Entity entity){
        if(containsSelfKey(key)) return false;
        hashMap.put(key , entity);
        return true;
    }

    public Entity getCheck(String name , String key){
        Entity entity = get(key);
        if(entity == null) throw new Error("entity not found");
        return entity;
    }

    public Entity getCheck(Location loc , String name , String key){
        Entity entity = get(key);
        if(entity == null) throw new Error("entity not found");
        return entity;
    }

    public void putCheck(String name , String key , Entity entity){
        if(!put(key , entity)) throw new Error("name defined");
    }

    public void checkContainsKey(String name , String key){
        if(!containsExactKey(key)) throw new Error("name not found");
    }

    public Entity getVarFuncCheck(String name){
        Entity entity;
        if(containsSelfExactKey(varKey(name))) entity = getSelf(varKey(name));
        else if(containsSelfExactKey(funcKey(name))) entity = getSelf(funcKey(name));
        else if(!isTop) entity = parent.getVarFuncCheck(name);
        else throw new Error("entity not found");
        return entity;
    }

}
