package Mx_compiler.IR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IRRoot {
    private Map<String , IRFunc> funcs = new HashMap<>();
    private Map<String , IRFunc> builtInFuncs = new HashMap<>();
    private List<StaticData> staticDataList = new ArrayList<>();
    private Map<String , StaticString> staticStringMap = new HashMap<>();

    public IRRoot(){
        //TODO
    }

    public void addFunc(IRFunc irFunc){
        funcs.put(irFunc.getName() , irFunc);
    }

    public IRFunc getIRFunc(String funcName){
        return funcs.get(funcName);
    }

    public void addStaticData(StaticData staticData){
        staticDataList.add(staticData);
    }

    public void addStaticStrings(String name , StaticString staticString){
        staticStringMap.put(name , staticString);
    }

    public StaticString getStaticString(String name){
        return staticStringMap.get(name);
    }

    public Map<String , StaticString> getStaticStringMap(){
        return staticStringMap;
    }

    public Map<String , IRFunc> getFuncs(){
        return funcs;
    }

    public List<StaticData> getStaticDataList(){
        return staticDataList;
    }
}
