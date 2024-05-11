package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * 添加一个残骸区
 * 残骸区添加船
 * 人物编辑器
 */
public class Controller {
    @FXML
    VBox vBox;
    @FXML
    CheckBox generateEntrancesAtGasGiants;
    @FXML
    CheckBox generateFringeJumpPoint;
    @FXML
    private TextField name;
    @FXML
    private Button addStar;
    @FXML
    private Button addCustomEntity;
    @FXML
    private Button addAsteroidBelt;
    @FXML
    private SplitPane splitPane;
    @FXML
    SplitPane firstStar;
    @FXML
    Separator firstTop;
    @FXML
    Text firstStart;
    @FXML
    TextField starName;
    @FXML
    ComboBox starType;
    @FXML
    TextField starRadius;
    @FXML
    TextField starCoronaSize;
    @FXML
    Button firstAddPlanet;
    @FXML
    Text firstEnd;
    @FXML
    Separator firstBottom;

    private List<Star> starList = new ArrayList<Star>();
    private List<SplitPane> asteroidBeltList = new ArrayList<SplitPane>();
    private List<SplitPane> customEntitytList = new ArrayList<SplitPane>();
    private List<SplitPane> starPaneList = new ArrayList<SplitPane>();
    private List<SplitPane> marketPaneList = new ArrayList<SplitPane>();
    private List<SplitPane> planetPaneList = new ArrayList<SplitPane>();
    private List<SplitPane> industryPaneList = new ArrayList<SplitPane>();
    private Map<Button,Star> starMap = new HashMap<>();
    private Map<Button,Planet> planetMap = new HashMap<>();
    private Map<Button,Market> marketMap = new HashMap<>();
    @FXML
    protected void addStar(ActionEvent event) {
        addStar((Button) event.getSource());
    }

    @FXML
    protected Star addStar(Button addStar) {
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button Clicked");
        alert.setHeaderText("You clicked the button!");
        alert.setContentText("Congratulations, it works!");
        alert.showAndWait();
        */

        if (addStar!=null) {
            if (addStar.getScene()!=null) {
                Pane root = (Pane) addStar.getScene().getRoot();
                int num = starList.size()+1;
                SplitPane starPane = new SplitPane();
                starPane.setOrientation(Orientation.VERTICAL);
                Separator top = new Separator();
                top.setStyle("-fx-background-color: YELLOW;");
                starPane.getItems().add(top);
                Text start = new Text();
                start.setText("主星"+num+"开始");
                starPane.getItems().add(start);
                //主星名称
                TextField starName = new TextField();
                starName.setPromptText("请输入主星ID 参考值【TaoHuaYuan】");
                starName.setId("starName");
                starPane.getItems().add(starName);
                //主星类型
                ComboBox starType = new ComboBox();
                starType.setPromptText("请输入主星类型");
                starType.setId("starType");
                starType.getItems().addAll(
                        "nebula_center_old@老年星云",
                        "nebula_center_average@中年星云",
                        "nebula_center_young@年轻星云",
                        "star_neutron@中子星",
                        "black_hole@黑洞",
                        "star_yellow@黄恒星",
                        "star_white@白矮星",
                        "star_blue_giant@蓝巨星",
                        "star_blue_supergiant@蓝超巨星",
                        "star_orange@橙恒星",
                        "star_orange_giant@橙巨星",
                        "star_red_supergiant@红超巨星",
                        "star_red_giant@红巨星",
                        "star_red_dwarf@红矮星",
                        "star_browndwarf@棕矮星"
                );
                starType.setConverter(new StringConverter<String>() {
                    @Override
                    public String toString(String object) {
                        // 当选择框展开时显示的默认文本
                        return starType.getPromptText()+":"+object;
                    }

                    @Override
                    public String fromString(String string) {
                        // 选择一个选项后，选择框内显示的文本
                        return string;
                    }
                });
                starPane.getItems().add(starType);
                //主星范围
                TextField starRadius = new TextField();
                starRadius.setPromptText("请输入主星范围 参考值【600】");
                starRadius.setId("starRadius");
                starPane.getItems().add(starRadius);
                //日冕范围
                TextField starCoronaSize = new TextField();
                starCoronaSize.setPromptText("请输入日冕范围 参考值【350】");
                starCoronaSize.setId("starCoronaSize");
                starPane.getItems().add(starCoronaSize);
                //添加星球按钮
                Button addPlanet = new Button();
                addPlanet.setText("为主星添加一颗星球");
                addPlanet.setOnAction(e -> {
                    addPlanet(addPlanet);
                });
                starPane.getItems().add(addPlanet);
                Text end = new Text();
                end.setText("主星"+num+"结束");
                starPane.getItems().add(end);
                Separator bottom = new Separator();
                bottom.setStyle("-fx-background-color: YELLOW;");
                starPane.getItems().add(bottom);
                splitPane.getItems().add(splitPane.getItems().indexOf(addStar),starPane);//splitPane.getItems().indexOf(addStar),
                Star star = new Star(starPane,top,start,starName,starType,starRadius,starCoronaSize,addPlanet,end,bottom);
                starList.add(star);
                starMap.put(addPlanet,star);
                starPaneList.add(starPane);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("实在是不会写所以用alert刷新布局");
                alert.showAndWait();
                return star;
            }
            else {

            }
        }
        else {

        }
        return null;
    }
    @FXML
    protected void addPlanet(ActionEvent event) {
            addPlanet((Button) event.getSource());
    }

    protected Planet addPlanet(Button addPlanet) {
        Star star = starMap.get(addPlanet);
        int starIndex = starList.indexOf(star)+1;
        int planetIndex = 0;
        if (star!=null) {
            planetIndex = star.planetList.size();
        }
        else {
            star = new Star(firstStar,firstTop,firstStart,starName,starType,starRadius,starCoronaSize,addPlanet,firstEnd,firstBottom);
            starList.add(star);
            starMap.put(addPlanet,star);
        }
        SplitPane planetPane = new SplitPane();
        planetPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: BLUE;");
        planetPane.getItems().add(top);
        Text start = new Text();
        start.setText("主星"+starIndex+"行星"+planetIndex+"开始");
        planetPane.getItems().add(start);
        //主星名称
        TextField planetId = new TextField();
        planetId.setPromptText("请输入行星ID 参考值【cmc_planet_yuanming】");
        planetId.setId("planetId");
        planetPane.getItems().add(planetId);
        TextField planetName = new TextField();
        planetName.setPromptText("请输入行星名称 参考值【可以输入中文】");
        planetName.setId("planetName");
        planetPane.getItems().add(planetName);
        //主星类型
        ComboBox planetType = new ComboBox();
        planetType.setPromptText("请输入行星类型");
        planetType.setId("planetType");
        planetType.getItems().addAll(
                "gas_giant@气态巨行星",
                "ice_giant@冰巨星",
                "lava@熔岩行星",
                "lava_minor@熔岩小行星",
                "frozen@冰冻行星",
                "frozen1@冰冻行星",
                "frozen2@冰冻行星",
                "frozen3@冰冻行星",
                "barren@荒芜行星",
                "barren_castiron@荒芜行星",
                "barren2@荒芜行星",
                "barren3@荒芜行星",
                "barren_venuslike@荒芜行星",
                "toxic@剧毒行星",
                "toxic_cold@剧毒行星冷",
                "jungle@丛林行星",
                "terran@类地行星",
                "desert@沙漠行星",
                "desert1@沙漠行星",
                "arid@干旱行星",
                "cryovolcanic@冰火山行星",
                "rocky_metallic@岩石行星金属",
                "rocky_unstable@岩石行星不稳定",
                "water@海洋行星",
                "rocky_ice@岩石行星冰",
                "irradiated@辐射行星",
                "barren-bombarded@荒芜轰击地表",
                "tundra@苔原行星",
                "barren-desert@荒芜沙漠",
                "terran-eccentric@类地反常"
        );
        planetType.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return planetType.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        planetPane.getItems().add(planetType);
        //行星角度
        TextField planetAngle = new TextField();
        planetAngle.setPromptText("请输入行星角度 参考值【215】");
        planetAngle.setId("planetAngle");
        planetPane.getItems().add(planetAngle);
        //行星半径
        TextField planetRadius = new TextField();
        planetRadius.setPromptText("请输入行星半径 参考值【120】");
        planetRadius.setId("planetRadius");
        planetPane.getItems().add(planetRadius);
        //轨道半径
        TextField planetOrbitRadius = new TextField();
        planetOrbitRadius.setPromptText("请输入轨道半径 参考值【4500】");
        planetOrbitRadius.setId("planetOrbitRadius");
        planetPane.getItems().add(planetOrbitRadius);
        //轨道天数
        TextField planetOrbitDay = new TextField();
        planetOrbitDay.setPromptText("请输入轨道天数 参考值【365】");
        planetOrbitDay.setId("planetOrbitDay");
        planetPane.getItems().add(planetOrbitDay);
        //自定义描述ID
        TextField customDescriptionId = new TextField();
        customDescriptionId.setPromptText("请输入行星自定义描述ID 参考值【cmc_planet_mingyue】");
        customDescriptionId.setId("customDescriptionId");
        planetPane.getItems().add(customDescriptionId);
        //添加星球按钮
        Button addChildPlanet = new Button();
        addChildPlanet.setText("为行星添加一颗卫星");
        addChildPlanet.setId("addChildPlanet");
        addChildPlanet.setOnAction(e -> {
            addPlanetNoElse(addChildPlanet);
        });
        planetPane.getItems().add(addChildPlanet);
        //添加市场按钮
        Button addMarket = new Button();
        addMarket.setText("为行星添加市场");
        addMarket.setOnAction(e -> {
            addMarket(addMarket);
        });
        planetPane.getItems().add(addMarket);
        Text end = new Text();
        end.setText("主星"+starIndex+"行星"+planetIndex+"结束");
        planetPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: BLUE;");
        planetPane.getItems().add(bottom);
        //starList.get(index).getItems().add(starList.get(index).getItems().indexOf(addPlanetList.get(index)),planetPane);
        starMap.get(addPlanet).star.getItems().add(starMap.get(addPlanet).star.getItems().indexOf(addPlanet),planetPane);
        Planet planet = new Planet(planetPane,top,start,planetId,planetName,planetType,null,null,addMarket,addChildPlanet,end,bottom);
        star.planetList.add(planet);
        starMap.put(addPlanet,star);
        planetMap.put(addChildPlanet,planet);
        planetMap.put(addMarket,planet);
        planetPaneList.add(planetPane);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();

        return planet;
    }

    protected Planet addPlanetNoElse(Button addPlanet) {
        Planet planet = planetMap.get(addPlanet);
        int planetIndex = planet.planetList.indexOf(planet)+1;
        SplitPane planetPane = new SplitPane();
        planetPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: GREEN;");
        planetPane.getItems().add(top);
        Text start = new Text();
        start.setText("行星"+planetIndex+"卫星"+planet.planetList.size()+"开始");
        planetPane.getItems().add(start);
        //主星名称
        TextField planetId = new TextField();
        planetId.setPromptText("请输入卫星ID 参考值【cmc_planet_mingyue】");
        planetId.setId("planetId");
        planetPane.getItems().add(planetId);
        TextField planetName = new TextField();
        planetName.setPromptText("请输入卫星名称 参考值【cmc_planet_mingyue】");
        planetName.setId("planetName");
        planetPane.getItems().add(planetName);
        //主星类型
        ComboBox planetType = new ComboBox();
        planetType.setPromptText("请输入卫星类型");
        planetType.setId("planetType");
        planetType.getItems().addAll(
                "gas_giant@气态巨行星",
                "ice_giant@冰巨星",
                "lava@熔岩行星",
                "lava_minor@熔岩小行星",
                "frozen@冰冻行星",
                "frozen1@冰冻行星",
                "frozen2@冰冻行星",
                "frozen3@冰冻行星",
                "barren@荒芜行星",
                "barren_castiron@荒芜行星",
                "barren2@荒芜行星",
                "barren3@荒芜行星",
                "barren_venuslike@荒芜行星",
                "toxic@剧毒行星",
                "toxic_cold@剧毒行星冷",
                "jungle@丛林行星",
                "terran@类地行星",
                "desert@沙漠行星",
                "desert1@沙漠行星",
                "arid@干旱行星",
                "cryovolcanic@冰火山行星",
                "rocky_metallic@岩石行星金属",
                "rocky_unstable@岩石行星不稳定",
                "water@海洋行星",
                "rocky_ice@岩石行星冰",
                "irradiated@辐射行星",
                "barren-bombarded@荒芜轰击地表",
                "tundra@苔原行星",
                "barren-desert@荒芜沙漠",
                "terran-eccentric@类地反常"
        );
        planetType.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return planetType.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        planetPane.getItems().add(planetType);
        //行星角度
        TextField planetAngle = new TextField();
        planetAngle.setPromptText("请输入卫星角度 参考值【90】");
        planetAngle.setId("planetAngle");
        planetPane.getItems().add(planetAngle);
        //行星半径
        TextField planetRadius = new TextField();
        planetRadius.setPromptText("请输入卫星半径 参考值【60】");
        planetRadius.setId("planetRadius");
        planetPane.getItems().add(planetRadius);
        //轨道半径
        TextField planetOrbitRadius = new TextField();
        planetOrbitRadius.setPromptText("请输入轨道半径 参考值【1000】");
        planetOrbitRadius.setId("planetOrbitRadius");
        planetPane.getItems().add(planetOrbitRadius);
        //轨道天数
        TextField planetOrbitDay = new TextField();
        planetOrbitDay.setPromptText("请输入轨道天数 参考值【30】");
        planetOrbitDay.setId("planetOrbitDay");
        planetPane.getItems().add(planetOrbitDay);
        //添加市场按钮
        Button addMarket = new Button();
        addMarket.setText("为卫星添加市场");
        addMarket.setOnAction(e -> {
            addMarket(addMarket);
        });
        planetPane.getItems().add(addMarket);
        Text end = new Text();
        end.setText("行星"+planetIndex+"卫星"+planet.planetList.size()+"结束");
        planetPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: GREEN;");
        planetPane.getItems().add(bottom);
        //starList.get(index).getItems().add(starList.get(index).getItems().indexOf(addPlanetList.get(index)),planetPane);
        planetMap.get(addPlanet).planet.getItems().add(planetMap.get(addPlanet).planet.getItems().indexOf(addPlanet),planetPane);
        Planet childPlanet = new Planet(planetPane,top,start,planetId,planetName,planetType,null,null,addMarket,null,end,bottom);
        planet.planetList.add(childPlanet);
        planetMap.put(addMarket,childPlanet);
        planetPaneList.add(planetPane);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();

        return childPlanet;
    }

    protected Market addMarket(Button addMarket) {
        Planet planet = planetMap.get(addMarket);
        int planetIndex = planet.planetList.indexOf(planet)+1;
        SplitPane marketPane = new SplitPane();
        marketPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: RED;");
        marketPane.getItems().add(top);
        Text start = new Text();
        start.setText(planet.start.getText()+"市场开始");
        marketPane.getItems().add(start);
        //factionId势力
        TextField factionId = new TextField();
        factionId.setPromptText("请输入势力ID 参考值【cmc】");
        factionId.setId("factionId");
        marketPane.getItems().add(factionId);
        //connectedEntities关联对象
        ComboBox connectedEntities = new ComboBox();
        connectedEntities.setPromptText("请输入关联对象 【可不填】");
        connectedEntities.setId("connectedEntities");
        marketPane.getItems().add(connectedEntities);
        //size规模
        TextField size = new TextField();
        size.setPromptText("请输入规模 参考值【6】");
        size.setId("size");
        marketPane.getItems().add(size);
        //Conditions 条件BUFF
        ComboBox conditions = new ComboBox();
        conditions.setPromptText("请输入条件BUFF多选");
        SplitPane conditionPane1 = new SplitPane();
        conditionPane1.setOrientation(Orientation.HORIZONTAL);
        //通信继电器
        //public static final String COMM_RELAY = "comm_relay";
        CheckBox comm_relay = new CheckBox();
        comm_relay.setText("通信继电器");
        comm_relay.setId("comm_relay");
        //敌对行动
        /*
        //public static final String HOSTILE_ACTIVITY = "hostile_activity";
        CheckBox hostile_activity = new CheckBox();
        hostile_activity.setText("敌对行动");
        hostile_activity.setId("hostile_activity");
        //海盗活动
        //public static final String PIRATE_ACTIVITY = "pirate_activity";
        CheckBox pirate_activity = new CheckBox();
        pirate_activity.setText("海盗袭击");
        pirate_activity.setId("pirate_activity");
        //卢德内奸
        //public static final String PATHER_CELLS = "pather_cells";
        CheckBox pather_cells = new CheckBox();
        pather_cells.setText("左径狂信徒");
        pather_cells.setId("pather_cells");
        conditionPane1.getItems().addAll(comm_relay,hostile_activity,pirate_activity,pather_cells);
         */
        //城市化政治
        //public static final String URBANIZED_POLITY = "urbanized_polity";
        CheckBox urbanized_polity = new CheckBox();
        urbanized_polity.setText("城市化");
        urbanized_polity.setId("urbanized_polity");
        //乡村政治
        //public static final String RURAL_POLITY = "rural_polity";
        CheckBox rural_polity = new CheckBox();
        rural_polity.setText("村镇化");
        rural_polity.setId("rural_polity");
        //工业政体
        //public static final String INDUSTRIAL_POLITY = "industrial_polity";
        CheckBox industrial_polity = new CheckBox();
        industrial_polity.setText("工业化");
        industrial_polity.setId("industrial_polity");
        conditionPane1.getItems().addAll(urbanized_polity,rural_polity,industrial_polity);
        //大量难民
        //public static final String LARGE_REFUGEE_POPULATION = "large_refugee_population";
        CheckBox large_refugee_population = new CheckBox();
        large_refugee_population.setText("难民潮");
        large_refugee_population.setId("large_refugee_population");
        //龙虾
        //public static final String VOLTURNIAN_LOBSTER_PENS = "volturnian_lobster_pens";
        CheckBox volturnian_lobster_pens = new CheckBox();
        volturnian_lobster_pens.setText("蓝龙虾养殖场");
        volturnian_lobster_pens.setId("volturnian_lobster_pens");
        //ChoiceBox
        //public static final String VICE_DEMAND = "vice_demand";
        CheckBox vice_demand = new CheckBox();
        vice_demand.setText("违禁品需求");
        vice_demand.setId("vice_demand");

        conditionPane1.getItems().addAll(large_refugee_population,volturnian_lobster_pens,vice_demand);
        marketPane.getItems().add(conditionPane1);
        SplitPane conditionPane4 = new SplitPane();
        conditionPane4.setOrientation(Orientation.HORIZONTAL);
        //有组织犯罪
        //public static final String ORGANIZED_CRIME = "organized_crime";
        CheckBox organized_crime = new CheckBox();
        organized_crime.setText("犯罪组织");
        organized_crime.setId("organized_crime");
        //边界
        //public static final String FRONTIER = "frontier";
        CheckBox frontier = new CheckBox();
        frontier.setText("边境之地");
        frontier.setId("frontier");
        //不文明
        //public static final String DECIVILIZED = "decivilized";
        CheckBox decivilized = new CheckBox();
        decivilized.setText("荒蛮之地");
        decivilized.setId("decivilized");
        //非法狂徒
        //public static final String DECIVILIZED_SUBPOP = "decivilized_subpop";
        CheckBox decivilized_subpop = new CheckBox();
        decivilized_subpop.setText("法外之地");
        decivilized_subpop.setId("decivilized_subpop");
        //废弃空间站
        //public static final String ABANDONED_STATION = "abandoned_station";
        CheckBox abandoned_station = new CheckBox();
        abandoned_station.setText("废弃空间站");
        abandoned_station.setId("abandoned_station");
        //废弃空间站
        //public static final String ABANDONED_STATION = "abandoned_station";
        CheckBox cottage_industry = new CheckBox();
        cottage_industry.setText("家庭手工业");
        cottage_industry.setId("cottage_industry");
        //前哨
        //public static final String OUTPOST = "outpost";
        CheckBox outpost = new CheckBox();
        outpost.setText("前哨");
        outpost.setId("outpost");
        conditionPane4.getItems().addAll(organized_crime,frontier,decivilized,abandoned_station,cottage_industry,outpost);
        //卢德多数
        //public static final String LUDDIC_MAJORITY = "luddic_majority";
        CheckBox luddic_majority = new CheckBox();
        luddic_majority.setText("卢德教众");
        luddic_majority.setId("luddic_majority");
        //隐形雷区
        //public static final String STEALTH_MINEFIELDS = "stealth_minefields";
        CheckBox stealth_minefields = new CheckBox();
        stealth_minefields.setText("隐形雷区");
        stealth_minefields.setId("stealth_minefields");
        //区域性资本
        //public static final String REGIONAL_CAPITAL = "regional_capital";
        CheckBox regional_capital = new CheckBox();
        regional_capital.setText("地区首府");
        regional_capital.setId("regional_capital");
        //自由市场
        //public static final String FREE_PORT = "free_market";
        CheckBox free_market = new CheckBox();
        free_market.setText("自由市场");
        free_market.setId("free_market");
        conditionPane4.getItems().addAll(luddic_majority,stealth_minefields,regional_capital,free_market);
        marketPane.getItems().add(conditionPane4);
        SplitPane conditionPane6 = new SplitPane();
        conditionPane6.setOrientation(Orientation.HORIZONTAL);
        //流氓AI核心
        //public static final String ROGUE_AI_CORE = "rogue_ai_core";
        CheckBox rogue_ai_core = new CheckBox();
        rogue_ai_core.setText("流氓AI核心");
        rogue_ai_core.setId("rogue_ai_core");
        //AI执政官
        //public static final String AI_CORE_ADMIN = "ai_core_admin";
        CheckBox ai_core_admin = new CheckBox();
        ai_core_admin.setText("AI执政官");
        ai_core_admin.setId("ai_core_admin");
        //封闭式移民
        //public static final String CLOSED_IMMIGRATION = "closed_immigration";
        CheckBox closed_immigration = new CheckBox();
        closed_immigration.setText("禁止移民");
        closed_immigration.setId("closed_immigration");
        //太阳能电池阵列
        //public static final String SOLAR_ARRAY = "solar_array";
        CheckBox solar_array = new CheckBox();
        solar_array.setText("轨道光照阵列");
        solar_array.setId("solar_array");
        conditionPane6.getItems().addAll(rogue_ai_core,ai_core_admin,closed_immigration,solar_array);
        //太空港
        //public static final String SPACEPORT = "spaceport";
        /*
        CheckBox spaceport = new CheckBox();
        spaceport.setText("太空港");
        spaceport.setId("spaceport");
         */
        //持不同政见者
        //public static final String DISSIDENT = "dissident";
        CheckBox dissident = new CheckBox();
        dissident.setText("异议人士");
        dissident.setId("dissident");
        //持不同政见者
        //public static final String DISSIDENT = "dissident";
        CheckBox headquarters = new CheckBox();
        headquarters.setText("指挥中心");
        headquarters.setId("headquarters");
        //持不同政见者
        //public static final String DISSIDENT = "dissident";
        CheckBox hydroponics_complex = new CheckBox();
        hydroponics_complex.setText("水培复合体");
        hydroponics_complex.setId("hydroponics_complex");
        //轨道燃烧
        /*
        //public static final String ORBITAL_BURNS = "orbital_burns";
        CheckBox orbital_burns = new CheckBox();
        orbital_burns.setText("轨道燃烧");
        orbital_burns.setId("orbital_burns");
         */
        conditionPane6.getItems().addAll(dissident,headquarters,hydroponics_complex);//spaceport,
        marketPane.getItems().add(conditionPane6);
        //冰冻
        //public static final String ICE = "ice";
        //沙漠
        //public static final String DESERT = "desert";
        //干旱
        //public static final String ARID = "arid";
        //海水
        //public static final String WATER = "water";
        //丛林
        //public static final String JUNGLE = "jungle";
        //荒芜
        //public static final String TERRAN = "terran";
        SplitPane conditionPane7 = new SplitPane();
        conditionPane7.setOrientation(Orientation.HORIZONTAL);
        ComboBox plantType = new ComboBox();
        plantType.setPromptText("星球地貌");
        plantType.setId("plantType");
        plantType.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return plantType.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        plantType.getItems().addAll("uninhabitable@死寂","ice@冰冻","desert@沙漠","arid@干旱","water@海洋",
                "jungle@丛林","terran@宜居","barren_marginal@贫瘠","twilight@暮光之地","tundra@苔原地貌","cryovolcanic@冰火山地貌"
        );
        //人口规模
        //public static final String POPULATION_1 = "population_1";
        //public static final String POPULATION_2 = "population_2";
        //public static final String POPULATION_3 = "population_3";
        //public static final String POPULATION_4 = "population_4";
        //public static final String POPULATION_5 = "population_5";
        //public static final String POPULATION_6 = "population_6";
        //public static final String POPULATION_7 = "population_7";
        //public static final String POPULATION_8 = "population_8";
        //public static final String POPULATION_9 = "population_9";
        //public static final String POPULATION_10 = "population_10";
        SplitPane conditionPane8 = new SplitPane();
        conditionPane8.setOrientation(Orientation.HORIZONTAL);
        ComboBox population = new ComboBox();
        population.setPromptText("人口规模");
        population.setId("population");
        population.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return population.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        population.getItems().addAll("population_1@1","population_2@2","population_3@3","population_4@4",
                "population_5@5","population_6@6","population_7@7","population_8@8",
                "population_9@9","population_10@10"
        );
        /*
        //最近动乱
        //public static final String RECENT_UNREST = "recent_unrest";
        CheckBox recent_unrest = new CheckBox();
        recent_unrest.setText("最近动乱");
        recent_unrest.setId("recent_unrest");
        //事件粮食短缺
        //public static final String EVENT_FOOD_SHORTAGE = "event_food_shortage";
        CheckBox event_food_shortage = new CheckBox();
        event_food_shortage.setText("食品短缺");
        event_food_shortage.setId("event_food_shortage");
        //航运中断
        //public static final String SHIPPING_DISRUPTION = "shipping_disruption";
        CheckBox shipping_disruption = new CheckBox();
        shipping_disruption.setText("贸易中断");
        shipping_disruption.setId("shipping_disruption");
        //航运中断
        //public static final String SHIPPING_DISRUPTION = "shipping_disruption";
        CheckBox event_trade_disruption = new CheckBox();
        event_trade_disruption.setText("贸易中断");
        event_trade_disruption.setId("event_trade_disruption");
        //航运中断
        //public static final String SHIPPING_DISRUPTION = "shipping_disruption";
        CheckBox event_smuggling = new CheckBox();
        event_smuggling.setText("走私");
        event_smuggling.setId("event_smuggling");
        //航运中断
        //public static final String SHIPPING_DISRUPTION = "shipping_disruption";
        CheckBox event_bounty = new CheckBox();
        event_bounty.setText("星系悬赏");
        event_bounty.setId("event_bounty");
         */
        //宜居的
        //public static final String HABITABLE = "habitable";
        CheckBox habitable = new CheckBox();
        habitable.setText("宜居的");
        habitable.setId("habitable");
        conditionPane8.getItems().addAll(population,habitable);//,recent_unrest,event_food_shortage,shipping_disruption,event_trade_disruption,event_smuggling,event_bounty,
        marketPane.getItems().add(conditionPane8);
        SplitPane conditionPane9 = new SplitPane();
        conditionPane9.setOrientation(Orientation.HORIZONTAL);
        //散落的废墟
        //public static final String RUINS_SCATTERED = "ruins_scattered";
        //public static final String RUINS_WIDESPREAD = "ruins_widespread";
        //public static final String RUINS_EXTENSIVE = "ruins_extensive";
        //public static final String RUINS_VAST = "ruins_vast";
        ComboBox ruins = new ComboBox();
        ruins.setPromptText("废墟");
        ruins.setId("ruins");
        ruins.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return ruins.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        ruins.getItems().addAll("","ruins_scattered@散落的废墟","ruins_widespread@普遍的废墟","ruins_extensive@广阔的废墟","ruins_vast@巨大的废墟");
        //
        //public static final String COLD = "cold";
        //public static final String VERY_COLD = "very_cold";
        //
        //public static final String HOT = "hot";
        //public static final String VERY_HOT = "very_hot";
        ComboBox coldhot = new ComboBox();
        coldhot.setPromptText("冷热");
        coldhot.setId("coldhot");
        coldhot.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return coldhot.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        coldhot.getItems().addAll("","cold@冷","very_cold@好冷","hot@热","very_hot@好热");
        //不稳定板块
        //public static final String TECTONIC_ACTIVITY = "tectonic_activity";
        //public static final String EXTREME_TECTONIC_ACTIVITY = "extreme_tectonic_activity";
        ComboBox tectonic = new ComboBox();
        tectonic.setPromptText("板块");
        tectonic.setId("tectonic");
        tectonic.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return tectonic.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        tectonic.getItems().addAll("","tectonic_activity@不稳定","extreme_tectonic_activity@极度不稳定");
        //无大气
        //public static final String NO_ATMOSPHERE = "no_atmosphere";
        //public static final String THIN_ATMOSPHERE = "thin_atmosphere";
        //public static final String TOXIC_ATMOSPHERE = "toxic_atmosphere";
        //public static final String DENSE_ATMOSPHERE = "dense_atmosphere";
        ComboBox atmosphere = new ComboBox();
        atmosphere.setPromptText("大气");
        atmosphere.setId("atmosphere");
        atmosphere.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return atmosphere.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        atmosphere.getItems().addAll("","no_atmosphere@无","thin_atmosphere@稀薄","toxic_atmosphere@有毒","dense_atmosphere@稠密");
        conditionPane9.getItems().addAll(ruins,coldhot,tectonic,atmosphere);
        marketPane.getItems().add(conditionPane9);
        SplitPane conditionPane10 = new SplitPane();
        conditionPane10.setOrientation(Orientation.HORIZONTAL);
        //温和气候
        //public static final String MILD_CLIMATE = "mild_climate";
        //public static final String EXTREME_WEATHER = "extreme_weather";
        ComboBox weather = new ComboBox();
        weather.setPromptText("气候");
        weather.setId("weather");
        weather.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return weather.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        weather.getItems().addAll("","mild_climate@温和","extreme_weather@极端");
        //重力
        //public static final String LOW_GRAVITY = "low_gravity";
        //public static final String HIGH_GRAVITY = "high_gravity";
        ComboBox gravity = new ComboBox();
        gravity.setPromptText("重力");
        gravity.setId("gravity");
        gravity.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return gravity.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        gravity.getItems().addAll("","low_gravity@高","high_gravity@低");
        //少光
        //public static final String POOR_LIGHT = "poor_light";
        //public static final String DARK = "dark";
        ComboBox light = new ComboBox();
        light.setPromptText("光照");
        light.setId("light");
        light.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return light.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        light.getItems().addAll("","poor_light@少光","dark@黑暗");

        conditionPane10.getItems().addAll(weather,gravity,light);
        marketPane.getItems().add(conditionPane10);
        SplitPane conditionPane11 = new SplitPane();
        conditionPane11.setOrientation(Orientation.HORIZONTAL);

        //流星撞击
        //public static final String METEOR_IMPACTS = "meteor_impacts";
        CheckBox meteor_impacts = new CheckBox();
        meteor_impacts.setText("流星撞击");
        meteor_impacts.setId("meteor_impacts");
        //污染
        //public static final String POLLUTION = "pollution";
        CheckBox pollution = new CheckBox();
        pollution.setText("污染");
        pollution.setId("pollution");
        //辐射
        //public static final String IRRADIATED = "irradiated";
        CheckBox irradiated = new CheckBox();
        irradiated.setText("辐射");
        irradiated.setId("irradiated");
        //敌对生物圈
        //public static final String INIMICAL_BIOSPHERE = "inimical_biosphere";
        CheckBox inimical_biosphere = new CheckBox();
        inimical_biosphere.setText("敌对生物圈");
        inimical_biosphere.setId("inimical_biosphere");
        //水覆盖
        //public static final String WATER_SURFACE = "water_surface";
        CheckBox water_surface = new CheckBox();
        water_surface.setText("海洋覆盖");
        water_surface.setId("water_surface");
        conditionPane11.getItems().addAll(plantType,meteor_impacts,pollution,irradiated,inimical_biosphere,water_surface);
        marketPane.getItems().add(conditionPane11);
        SplitPane conditionPane12 = new SplitPane();
        conditionPane12.setOrientation(Orientation.HORIZONTAL);
        //矿石
        //public static final String ORE_SPARSE = "ore_sparse";
        //public static final String ORE_MODERATE = "ore_moderate";
        //public static final String ORE_ABUNDANT = "ore_abundant";
        //public static final String ORE_RICH = "ore_rich";
        //public static final String ORE_ULTRARICH = "ore_ultrarich";
        ComboBox ore = new ComboBox();
        ore.setPromptText("矿石");
        ore.setId("ore");
        ore.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return ore.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        ore.getItems().addAll("","ore_sparse@贫瘠","ore_moderate@一般","ore_abundant@富饶","ore_rich@超富","ore_ultrarich@富爆");
        //稀有矿石
        //public static final String RARE_ORE_SPARSE = "rare_ore_sparse";
        //public static final String RARE_ORE_MODERATE = "rare_ore_moderate";
        //public static final String RARE_ORE_ABUNDANT = "rare_ore_abundant";
        //public static final String RARE_ORE_RICH = "rare_ore_rich";
        //public static final String RARE_ORE_ULTRARICH = "rare_ore_ultrarich";
        ComboBox rare_ore = new ComboBox();
        rare_ore.setPromptText("稀有矿石");
        rare_ore.setId("rare_ore");
        rare_ore.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return rare_ore.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        rare_ore.getItems().addAll("","rare_ore_sparse@贫瘠","rare_ore_moderate@一般","rare_ore_abundant@富饶","rare_ore_rich@超富","rare_ore_ultrarich@富爆");
        //挥发物
        //public static final String VOLATILES_TRACE = "volatiles_trace";
        //public static final String VOLATILES_DIFFUSE = "volatiles_diffuse";
        //public static final String VOLATILES_ABUNDANT = "volatiles_abundant";
        //public static final String VOLATILES_PLENTIFUL = "volatiles_plentiful";
        ComboBox volatiles = new ComboBox();
        volatiles.setPromptText("挥发物");
        volatiles.setId("volatiles");
        volatiles.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return volatiles.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        volatiles.getItems().addAll("","volatiles_trace@贫瘠","volatiles_diffuse@一般","volatiles_abundant@富饶","volatiles_plentiful@超富");
        //有机物
        //public static final String ORGANICS_TRACE = "organics_trace";
        //public static final String ORGANICS_COMMON = "organics_common";
        //public static final String ORGANICS_ABUNDANT = "organics_abundant";
        //public static final String ORGANICS_PLENTIFUL = "organics_plentiful";
        ComboBox organics = new ComboBox();
        organics.setPromptText("有机物");
        organics.setId("organics");
        organics.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return organics.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        organics.getItems().addAll("","organics_trace@贫瘠","organics_common@一般","organics_abundant@富饶","organics_plentiful@超富");
        //农田
        //public static final String FARMLAND_POOR = "farmland_poor";
        //public static final String FARMLAND_ADEQUATE = "farmland_adequate";
        //public static final String FARMLAND_RICH = "farmland_rich";
        //public static final String FARMLAND_BOUNTIFUL = "farmland_bountiful";
        ComboBox farmland = new ComboBox();
        farmland.setPromptText("农田");
        farmland.setId("farmland");
        farmland.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return farmland.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        farmland.getItems().addAll("","farmland_poor@贫瘠","farmland_adequate@一般","farmland_adequate@富饶","farmland_adequate@超富");
        conditionPane12.getItems().addAll(ore,rare_ore,volatiles,organics,farmland);
        marketPane.getItems().add(conditionPane12);
        //改成多选
        //marketPane.getItems().add(conditions);
        //Submarkets 二级市场
        ComboBox submarkets = new ComboBox();
        submarkets.setPromptText("请输入二级市场多选");
        //改成多选
        //marketPane.getItems().add(submarkets);
        SplitPane submarketPane = new SplitPane();
        submarketPane.setOrientation(Orientation.HORIZONTAL);
        List<CheckBox> submarketList = new ArrayList<>();
        //军事市场
        CheckBox genericMilitary = new CheckBox();
        genericMilitary.setText("军事市场");
        genericMilitary.setId("generic_military");
        submarketList.add(genericMilitary);
        //库存
        CheckBox storage = new CheckBox();
        storage.setText("库存");
        storage.setId("storage");
        submarketList.add(storage);
        //公开市场
        CheckBox openMarket = new CheckBox();
        openMarket.setText("公开市场");
        openMarket.setId("open_market");
        submarketList.add(openMarket);
        //黑市
        CheckBox blackMarket = new CheckBox();
        blackMarket.setText("黑市");
        blackMarket.setId("black_market");
        submarketList.add(blackMarket);
        submarketPane.getItems().addAll(submarketList);
        marketPane.getItems().add(submarketPane);
        //Industries 建筑
        //ComboBox industries = new ComboBox();
        //industries.setPromptText("请输入建筑");
        //marketPane.getItems().add(industries);
        //添加市场按钮
        Button addIndustry = new Button();
        addIndustry.setText("为市场添加建筑");
        addIndustry.setOnAction(e -> {
            addIndustry(addIndustry);
        });
        marketPane.getItems().add(addIndustry);
        //tarrif 税率
        TextField tarrif = new TextField();
        tarrif.setPromptText("请输入税率");
        tarrif.setId("tarrif");
        marketPane.getItems().add(tarrif);
        //freePort 自由港
        CheckBox freePort = new CheckBox();
        freePort.setText("自由港");
        freePort.setId("freePort");
        marketPane.getItems().add(freePort);
        Text end = new Text();
        end.setText(planet.end.getText()+"市场结束");
        marketPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: RED;");
        marketPane.getItems().add(bottom);
        planetMap.get(addMarket).planet.getItems().add(planetMap.get(addMarket).planet.getItems().indexOf(addMarket),marketPane);

        Market market = new Market(marketPane,top,start,factionId,connectedEntities,size,conditions,submarketList,addIndustry,tarrif,freePort,end,bottom);
        market.planet = planet;
        marketMap.put(addIndustry,market);
        planetMap.put(addIndustry,planet);
        marketPaneList.add(marketPane);
        planet.planet.getItems().remove(addMarket);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
        return market;
    }
    @FXML
    protected void addAsteroidBelt(ActionEvent event) {
        addAsteroidBelt((Button) event.getSource());
    }
    protected AsteroidBelt addAsteroidBelt(Button addAsteroidBelt) {
        SplitPane asteroidBeltPane = new SplitPane();
        asteroidBeltPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: PINK;");
        asteroidBeltPane.getItems().add(top);
        Text start = new Text();
        start.setText("陨石带"+asteroidBeltList.size()+"开始");
        asteroidBeltPane.getItems().add(start);
        //focus环绕ID
        TextField focus = new TextField();
        focus.setPromptText("轨道焦点ID 参考值【TaoHuaYuan,上文中出现过的星球ID】");
        focus.setId("focus");
        asteroidBeltPane.getItems().add(focus);
        //numAsteroids陨石数量
        TextField numAsteroids = new TextField();
        numAsteroids.setPromptText("请输入陨石数量 参考值【100】");
        numAsteroids.setId("numAsteroids");
        asteroidBeltPane.getItems().add(numAsteroids);
        //orbitRadius轨道半径
        TextField orbitRadius = new TextField();
        orbitRadius.setPromptText("请输入轨道半径 参考值【2200】");
        orbitRadius.setId("orbitRadius");
        asteroidBeltPane.getItems().add(orbitRadius);
        //width宽度
        TextField width = new TextField();
        width.setPromptText("请输入轨道宽度 参考值【150】");
        width.setId("width");
        asteroidBeltPane.getItems().add(width);
        //minOrbitDays最小轨道天数
        TextField minOrbitDays = new TextField();
        minOrbitDays.setPromptText("请输入最小轨道天数 参考值【180】");
        minOrbitDays.setId("minOrbitDays");
        asteroidBeltPane.getItems().add(minOrbitDays);
        //maxOrbitDays最大轨道天数
        TextField maxOrbitDays = new TextField();
        maxOrbitDays.setPromptText("请输入最大轨道天数 参考值【360】");
        maxOrbitDays.setId("maxOrbitDays");
        asteroidBeltPane.getItems().add(maxOrbitDays);
        //optionalName可选名称
        TextField optionalName = new TextField();
        optionalName.setPromptText("请输入可选名称 参考值【可不填】");
        optionalName.setId("optionalName");
        asteroidBeltPane.getItems().add(optionalName);

        Text end = new Text();
        end.setText("陨石带"+asteroidBeltList.size()+"结束");
        asteroidBeltPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: PINK;");
        asteroidBeltPane.getItems().add(bottom);

        AsteroidBelt asteroidBelt = new AsteroidBelt(asteroidBeltPane,top,start,numAsteroids,orbitRadius,width,minOrbitDays,maxOrbitDays,optionalName,end,bottom);
        asteroidBeltList.add(asteroidBeltPane);
        splitPane.getItems().add(splitPane.getItems().indexOf(addAsteroidBelt),asteroidBeltPane);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();

        return asteroidBelt;
    }

    @FXML
    protected void addCustomEntity(ActionEvent event) {
        addCustomEntity((Button) event.getSource());
    }
    protected CustomEntity addCustomEntity(Button addCustomEntity) {
        SplitPane customEntityPane = new SplitPane();
        customEntityPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: PURPLE;");
        customEntityPane.getItems().add(top);
        Text start = new Text();
        start.setText("自定义道具"+customEntitytList.size()+"开始");
        customEntityPane.getItems().add(start);

        //id
        TextField id = new TextField();
        id.setPromptText("请输入ID 参考值【可不填】");
        id.setId("id");
        customEntityPane.getItems().add(id);
        //name名称
        TextField name = new TextField();
        name.setPromptText("请输入名称 参考值【可不填】");
        name.setId("name");
        customEntityPane.getItems().add(name);
        //type
        ComboBox type = new ComboBox();
        type.setPromptText("请输入类型");
        type.setId("type");
        type.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return type.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        List<String> typeList = new ArrayList<>();
        //轨道重工
        String STATION_BUILT_FROM_INDUSTRY = "station_built_from_industry";
        typeList.add(STATION_BUILT_FROM_INDUSTRY+"@轨道重工");
        //废弃探测器
        String DERELICT_SURVEY_PROBE = "derelict_probe";
        typeList.add(DERELICT_SURVEY_PROBE+"@废弃探测器");
        //废弃测量船
        String DERELICT_SURVEY_SHIP = "derelict_survey_ship";
        typeList.add(DERELICT_SURVEY_SHIP+"@废弃测量船");
        //废弃母船
        String DERELICT_MOTHERSHIP = "derelict_mothership";
        typeList.add(DERELICT_MOTHERSHIP+"@废弃母船");
        //废弃的低温睡眠器
        String DERELICT_CRYOSLEEPER = "derelict_cryosleeper";
        typeList.add(DERELICT_CRYOSLEEPER+"@废弃的低温睡眠器");
        //星冕分流器
        String CORONAL_TAP = "coronal_tap";
        typeList.add(CORONAL_TAP+"@星冕分流器");
        //残骸
        String DEBRIS_FIELD_SHARED = "debris_field_shared";
        typeList.add(DEBRIS_FIELD_SHARED+"@残骸");
        //通信中继器
        String COMM_RELAY = "comm_relay";
        typeList.add(COMM_RELAY+"@通信中继器");
        //导航浮标
        String NAV_BUOY = "nav_buoy";
        typeList.add(NAV_BUOY+"@导航浮标");
        //传感器阵列
        String SENSOR_ARRAY = "sensor_array";
        typeList.add(SENSOR_ARRAY+"@传感器阵列");
        //临时通信中继器
        String COMM_RELAY_MAKESHIFT = "comm_relay_makeshift";
        typeList.add(COMM_RELAY_MAKESHIFT+"@临时通信中继器");
        //临时导航浮标
        String NAV_BUOY_MAKESHIFT = "nav_buoy_makeshift";
        typeList.add(NAV_BUOY_MAKESHIFT+"@临时导航浮标");
        //临时传感器阵列
        String SENSOR_ARRAY_MAKESHIFT = "sensor_array_makeshift";
        typeList.add(SENSOR_ARRAY_MAKESHIFT+"@轨道重工");
        //稳定的位置
        String STABLE_LOCATION = "stable_location";
        typeList.add(STABLE_LOCATION+"@稳定的位置");
        //任务地点
        String MISSION_LOCATION = "mission_location";
        typeList.add(MISSION_LOCATION+"@任务地点");
        //融合灯
        String FUSION_LAMP = "fusion_lamp";
        typeList.add(FUSION_LAMP+"@融合灯");
        //通用探针
        String GENERIC_PROBE = "generic_probe";
        typeList.add(GENERIC_PROBE+"@通用探针");
        //特殊货舱
        String CARGO_POD_SPECIAL = "cargo_pod_special";
        typeList.add(CARGO_POD_SPECIAL+"@特殊货舱");
        //恒星反射镜
        String STELLAR_MIRROR = "stellar_mirror";
        typeList.add(STELLAR_MIRROR+"@恒星反射镜");
        //恒星阴影
        String STELLAR_SHADE = "stellar_shade";
        typeList.add(STELLAR_SHADE+"@恒星阴影");
        //非活动门
        String INACTIVE_GATE = "inactive_gate";
        typeList.add(INACTIVE_GATE+"@非活动门");
        //货舱
        String CARGO_PODS = "cargo_pods";
        typeList.add(CARGO_PODS+"@货舱");
        //警告航标
        String WARNING_BEACON = "warning_beacon";
        typeList.add(WARNING_BEACON+"@警告航标");
        //轨道船坞
        String ORBITAL_DOCKYARD = "orbital_dockyard";
        typeList.add(ORBITAL_DOCKYARD+"@轨道船坞");
        //采矿站遗迹
        String STATION_MINING = "station_mining_remnant";
        typeList.add(STATION_MINING+"@采矿站遗迹");
        //研究站遗迹
        String STATION_RESEARCH = "station_research_remnant";
        typeList.add(STATION_RESEARCH+"@研究站遗迹");
        //轨道栖息地遗迹
        String ORBITAL_HABITAT = "orbital_habitat_remnant";
        typeList.add(ORBITAL_HABITAT+"@轨道栖息地遗迹");
        //临时空间站
        String MAKESHIFT_STATION = "makeshift_station";
        typeList.add(MAKESHIFT_STATION+"@临时空间站");
        //采矿站遗迹
        String STATION_MINING_REMNANT = "station_mining_remnant";
        typeList.add(STATION_MINING_REMNANT+"@采矿站遗迹");
        //研究站遗迹
        String STATION_RESEARCH_REMNANT = "station_research_remnant";
        typeList.add(STATION_RESEARCH_REMNANT+"@研究站遗迹");
        //轨道栖息地遗迹
        String ORBITAL_HABITAT_REMNANT = "orbital_habitat_remnant";
        typeList.add(ORBITAL_HABITAT_REMNANT+"@轨道栖息地遗迹");
        //技术
        String TECHNOLOGY_CACHE = "technology_cache";
        typeList.add(TECHNOLOGY_CACHE+"@技术");
        //补给
        String SUPPLY_CACHE = "supply_cache";
        typeList.add(SUPPLY_CACHE+"@补给");
        //小补给
        String SUPPLY_CACHE_SMALL = "supply_cache_small";
        typeList.add(SUPPLY_CACHE_SMALL+"@小补给");
        //设备
        String EQUIPMENT_CACHE = "equipment_cache";
        typeList.add(EQUIPMENT_CACHE+"@设备");
        //小设备
        String EQUIPMENT_CACHE_SMALL = "equipment_cache_small";
        typeList.add(EQUIPMENT_CACHE_SMALL+"@小设备");
        //武器
        String WEAPONS_CACHE = "weapons_cache";
        typeList.add(WEAPONS_CACHE+"@武器");
        //武器低科
        String WEAPONS_CACHE_LOW = "weapons_cache_low";
        typeList.add(WEAPONS_CACHE_LOW+"@武器低科");
        //武器高科
        String WEAPONS_CACHE_HIGH = "weapons_cache_high";
        typeList.add(WEAPONS_CACHE_HIGH+"@武器高科");
        //废弃武器
        String WEAPONS_CACHE_REMNANT = "weapons_cache_remnant";
        typeList.add(WEAPONS_CACHE_REMNANT+"@废弃武器");
        //武器小
        String WEAPONS_CACHE_SMALL = "weapons_cache_small";
        typeList.add(WEAPONS_CACHE_SMALL+"@武器小");
        //武器小低科
        String WEAPONS_CACHE_SMALL_LOW = "weapons_cache_small_low";
        typeList.add(WEAPONS_CACHE_SMALL_LOW+"@武器小低科");
        //武器小高科
        String WEAPONS_CACHE_SMALL_HIGH = "weapons_cache_small_high";
        typeList.add(WEAPONS_CACHE_SMALL_HIGH+"@武器小高科");
        //武器小遗迹
        String WEAPONS_CACHE_SMALL_REMNANT = "weapons_cache_small_remnant";
        typeList.add(WEAPONS_CACHE_SMALL_REMNANT+"@武器小遗迹");
        //隐藏
        String HIDDEN_CACHE = "hidden_cache";
        typeList.add(HIDDEN_CACHE+"@隐藏");
        //阿尔法武器
        String ALPHA_SITE_WEAPONS_CACHE = "alpha_site_weapons_cache";
        typeList.add(ALPHA_SITE_WEAPONS_CACHE+"@阿尔法武器");
        //沉船
        String WRECK = "wreck";
        typeList.add(WRECK+"@沉船");
        //基本星座标签
        String BASE_CONSTELLATION_LABEL = "base_constellation_label";
        typeList.add(BASE_CONSTELLATION_LABEL+"@基本星座标签");
        //基础intel图标
        String BASE_INTEL_ICON = "base_intel_icon";
        typeList.add(BASE_INTEL_ICON+"@基础intel图标");
        //爆炸
        String EXPLOSION = "explosion";
        typeList.add(EXPLOSION+"@爆炸");
        //传感器主机
        String SENSOR_GHOST = "sensor_ghost";
        typeList.add(SENSOR_GHOST+"@传感器主机");
        type.getItems().addAll(typeList);
        customEntityPane.getItems().add(type);
        //factionId势力
        TextField factionId = new TextField();
        factionId.setPromptText("请输入势力ID 参考值【neutral】中立");
        factionId.setId("factionId");
        customEntityPane.getItems().add(factionId);
        //focus轨道焦点
        TextField focus = new TextField();
        focus.setPromptText("请输入轨道焦点ID 参考值【上文中出现过的星球ID】");
        focus.setId("focus");
        customEntityPane.getItems().add(focus);
        //angle角度
        TextField angle = new TextField();
        angle.setPromptText("请输入角度 参考值【30】");
        angle.setId("angle");
        customEntityPane.getItems().add(angle);
        //orbitRadius轨道半径
        TextField orbitRadius = new TextField();
        orbitRadius.setPromptText("请输入轨道半径 参考值【100】");
        orbitRadius.setId("orbitRadius");
        customEntityPane.getItems().add(orbitRadius);
        //orbitDays轨道天数
        TextField orbitDays = new TextField();
        orbitDays.setPromptText("请输入轨道天数 参考值【1】");
        orbitDays.setId("orbitDays");
        customEntityPane.getItems().add(orbitDays);

        Text end = new Text();
        end.setText("自定义道具"+customEntitytList.size()+"结束");
        customEntityPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: PURPLE;");
        customEntityPane.getItems().add(bottom);

        CustomEntity customEntity = new CustomEntity(customEntityPane,top,start,id,name,type,factionId,focus,angle,orbitRadius,orbitDays,end,bottom);
        customEntitytList.add(customEntityPane);
        splitPane.getItems().add(splitPane.getItems().indexOf(addCustomEntity),customEntityPane);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();

        return customEntity;
    }
    protected Industry addIndustry(Button addIndustry) {
        Market market = marketMap.get(addIndustry);

        SplitPane industryPane = new SplitPane();
        industryPane.setOrientation(Orientation.VERTICAL);
        Separator top = new Separator();
        top.setStyle("-fx-background-color: PURPLE;");
        industryPane.getItems().add(top);
        Text start = new Text();
        start.setText(market.planet.start.getText()+"市场建筑"+market.industryList.size()+"开始");
        industryPane.getItems().add(start);
        //类型
        ComboBox industries = new ComboBox();
        industries.setPromptText("请输入类型");
        industries.setId("industries");
        industries.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return industries.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        List<String> industryList = new ArrayList<>();
        //居民
        String POPULATION = "population";
        industryList.add(POPULATION+"@居民");
        //农田
        String FARMING = "farming";
        industryList.add(FARMING+"@农田");
        //水产
        String AQUACULTURE = "aquaculture";
        industryList.add(AQUACULTURE+"@水产");
        //狮子卫星
        String LIONS_GUARD = "lionsguard";
        industryList.add(LIONS_GUARD+"@狮子卫星");
        //采矿
        String MINING = "mining";
        industryList.add(MINING+"@采矿");
        //技术挖掘
        String TECHMINING = "techmining";
        industryList.add(TECHMINING+"@技术挖掘");
        //精炼
        String REFINING = "refining";
        industryList.add(REFINING+"@精炼");
        //太空港
        String SPACEPORT = "spaceport";
        industryList.add(SPACEPORT+"@太空港");
        //超级港口
        String MEGAPORT = "megaport";
        industryList.add(MEGAPORT+"@超级港口");
        //商业
        String COMMERCE = "commerce";
        industryList.add(COMMERCE+"@商业");
        //轻工业
        String LIGHTINDUSTRY = "lightindustry";
        industryList.add(LIGHTINDUSTRY+"@轻工业");
        //重工业
        String HEAVYINDUSTRY = "heavyindustry";
        industryList.add(HEAVYINDUSTRY+"@重工业");
        //轨道重工
        String ORBITALWORKS = "orbitalworks";
        industryList.add(ORBITALWORKS+"@轨道重工");
        //燃料
        String FUELPROD = "fuelprod";
        industryList.add(FUELPROD+"@燃料");
        //地面防御
        String GROUNDDEFENSES = "grounddefenses";
        industryList.add(GROUNDDEFENSES+"@地面防御");
        //重炮
        String HEAVYBATTERIES = "heavybatteries";
        industryList.add(HEAVYBATTERIES+"@重炮");
        //巡逻队
        String PATROLHQ = "patrolhq";
        industryList.add(PATROLHQ+"@巡逻队");
        //军事基地
        String MILITARYBASE = "militarybase";
        industryList.add(MILITARYBASE+"@军事基地");
        //最高指挥部
        String HIGHCOMMAND = "highcommand";
        industryList.add(HIGHCOMMAND+"@最高指挥部");
        //行星护盾
        String PLANETARYSHIELD = "planetaryshield";
        industryList.add(PLANETARYSHIELD+"@行星护盾");
        //星际驿站
        String WAYSTATION = "waystation";
        industryList.add(WAYSTATION+"@星际驿站");
        //冷冻室
        String CRYOSANCTUM = "cryosanctum";
        industryList.add(CRYOSANCTUM+"@冷冻室");
        //轨道站
        String ORBITALSTATION = "orbitalstation";
        industryList.add(ORBITALSTATION+"@轨道站");
        //作战基地
        String BATTLESTATION = "battlestation";
        industryList.add(BATTLESTATION+"@作战基地");
        //星际要塞
        String STARFORTRESS = "starfortress";
        industryList.add(STARFORTRESS+"@星际要塞");
        //轨道站中科
        String ORBITALSTATION_MID = "orbitalstation_mid";
        industryList.add(ORBITALSTATION_MID+"@轨道站中科");
        //作战基地中科
        String BATTLESTATION_MID = "battlestation_mid";
        industryList.add(BATTLESTATION_MID+"@作战基地中科");
        //星际要塞中科
        String STARFORTRESS_MID = "starfortress_mid";
        industryList.add(STARFORTRESS_MID+"@星际要塞中科");
        //轨道站高科
        String ORBITALSTATION_HIGH = "orbitalstation_high";
        industryList.add(ORBITALSTATION_HIGH+"@轨道站高科");
        //作战基地高科
        String BATTLESTATION_HIGH = "battlestation_high";
        industryList.add(BATTLESTATION_HIGH+"@作战基地高科");
        //星际要塞高科
        String STARFORTRESS_HIGH = "starfortress_high";
        industryList.add(STARFORTRESS_HIGH+"@星际要塞高科");
        //
        String TAG_POPULATION = "population";
        String TAG_PARENT = "parent_item";
        String TAG_SUB = "sub_item";
        String TAG_IMPORTANT = "important";
        String TAG_STATION = "station";
        String TAG_BATTLESTATION = "battlestation";
        String TAG_STARFORTRESS = "starfortress";
        String TAG_GROUNDDEFENSES = "grounddefenses";
        String TAG_PATROL = "patrol";
        String TAG_MILITARY = "military";
        String TAG_COMMAND = "command";
        String TAG_SPACEPORT = "spaceport";
        String TAG_TACTICAL_BOMBARDMENT = "tactical_bombardment";
        String TAG_NO_SATURATION_BOMBARDMENT = "no_saturation_bombardment";
        String TAG_USES_BLUEPRINTS = "uses_blueprints";
        String TAG_UNRAIDABLE = "unraidable";
        String TAG_HEAVYINDUSTRY = "heavyindustry";
        String TAG_INDUSTRY = "industry";
        String TAG_STRUCTURE = "structure";
        String TAG_RURAL = "rural";
        String TAG_INDUSTRIAL = "industrial";
        String TAG_URBAN = "urban";
        industries.getItems().addAll(industryList);
        industryPane.getItems().add(industries);
        //核心
        ComboBox commodities = new ComboBox();
        commodities.setPromptText("请输入核心");
        commodities.setId("commodities");
        commodities.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return commodities.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        commodities.getItems().addAll("","alpha_core@阿尔法","beta_core@贝塔","gamma_core@伽马");
        industryPane.getItems().add(commodities);
        //道具
        ComboBox items = new ComboBox();
        items.setPromptText("请输入道具");
        items.setId("items");
        items.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return items.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
        items.getItems().addAll("","corrupted_nanoforge@损坏的纳米锻造炉",
                "pristine_nanoforge@原始纳米锻造炉",
                "synchrotron@同步加速器",
                "orbital_fusion_lamp@轨道融合灯",
                "mantle_bore@地幔钻机",
                "catalytic_core@催化核心",
                "soil_nanites@土壤纳米岩",
                "biofactory_embryo@生物因子胚胎",
                "fullerene_spool@富勒烯线轴",
                "plasma_dynamo@等离子体发电机",
                "cryoarithmetic_engine@低温算法引擎",
                "drone_replicator@无人机复制器",
                "dealmaker_holosuite@交易撮合者全息图",
                "coronal_portal@星冕分流器"
        );
        industryPane.getItems().add(items);
        Text end = new Text();
        end.setText(market.planet.end.getText()+"市场建筑"+market.industryList.size()+"结束");
        industryPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: PURPLE;");
        industryPane.getItems().add(bottom);

        Industry industry = new Industry(industryPane,top,start,industries,commodities,items,end,bottom);
        market.industryList.add(industry);
        market.market.getItems().add(market.market.getItems().indexOf(addIndustry),industryPane);
        industryPaneList.add(industryPane);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();

        return industry;
    }
    public class Star {
        SplitPane star;
        Separator top;
        Text start;
        TextField starName;
        ComboBox starType;
        TextField starRadius;
        TextField starCoronaSize;
        Button addPlanet;
        Text end;
        Separator bottom;
        List<Planet> planetList = new ArrayList<>();
        public Star(SplitPane star,Separator top,Text start,TextField starName,ComboBox starType,TextField starRadius,TextField starCoronaSize,Button addPlanet,Text end,Separator bottom){
            this.star=star;
            this.top=top;
            this.start=start;
            this.starName=starName;
            this.starType=starType;
            this.starRadius=starRadius;
            this.starCoronaSize=starCoronaSize;
            this.addPlanet=addPlanet;
            this.end=end;
            this.bottom=bottom;
        }
    }

    public class Planet {
        SplitPane planet;
        Separator top;
        Text start;
        TextField planetId;
        TextField planetName;
        ComboBox planetType;
        TextField planetRadius;
        TextField planetCoronaSize;
        Button addMarket;
        Button addPlanet;
        Text end;
        Separator bottom;
        List<Planet> planetList = new ArrayList<>();
        public Planet(SplitPane planet,Separator top,Text start,TextField planetId,TextField planetName,ComboBox planetType,TextField planetRadius,TextField planetCoronaSize,Button addMarket,Button addPlanet,Text end,Separator bottom){
            this.planet=planet;
            this.top=top;
            this.start=start;
            this.planetId=planetId;
            this.planetName=planetName;
            this.planetType=planetType;
            this.planetRadius=planetRadius;
            this.planetCoronaSize=planetCoronaSize;
            this.addMarket=addMarket;
            this.addPlanet=addPlanet;
            this.end=end;
            this.bottom=bottom;
        }
    }

    public class Market {
        Planet planet;
        SplitPane market;
        Separator top;
        Text start;
        //factionId势力
        TextField factionId;
        //connectedEntities关联对象
        ComboBox connectedEntities;
        //size规模
        TextField size;
        //Conditions 条件BUFF
        ComboBox conditions;
        //Submarkets 二级市场
        List<CheckBox> submarkets;
        //Industries 建筑
        Button industries;
        //tarrif 税率
        TextField tarrif;
        //freePort 自由港
        CheckBox freePort;
        Text end;
        Separator bottom;
        List<Industry> industryList = new ArrayList<>();
        public Market(SplitPane market,Separator top,Text start,TextField factionId,ComboBox connectedEntities,TextField size,ComboBox conditions,List<CheckBox> submarkets,Button industries,TextField tarrif,CheckBox freePort,Text end,Separator bottom){
            this.market=market;
            this.top=top;
            this.start=start;
            this.factionId=factionId;
            this.connectedEntities=connectedEntities;
            this.size=size;
            this.conditions=conditions;
            this.submarkets=submarkets;
            this.industries=industries;
            this.tarrif=tarrif;
            this.freePort=freePort;
            this.end=end;
            this.bottom=bottom;
        }
    }

    public class AsteroidBelt {
        SplitPane asteroidBelt;
        Separator top;
        Text start;
        //numAsteroids陨石数量
        TextField numAsteroids;
        //orbitRadius轨道半径
        TextField orbitRadius;
        //width宽度
        TextField width;
        //minOrbitDays最小轨道天数
        TextField minOrbitDays;
        //maxOrbitDays最大鬼道天数
        TextField maxOrbitDays;
        //optionalName可选名称
        TextField optionalName;
        Text end;
        Separator bottom;
        public AsteroidBelt(SplitPane asteroidBelt,Separator top,Text start,TextField numAsteroids,TextField orbitRadius,TextField width,TextField minOrbitDays,TextField maxOrbitDays,TextField optionalName,Text end,Separator bottom){
            this.asteroidBelt=asteroidBelt;
            this.top=top;
            this.start=start;
            this.numAsteroids=numAsteroids;
            this.orbitRadius=orbitRadius;
            this.width=width;
            this.minOrbitDays=minOrbitDays;
            this.maxOrbitDays=maxOrbitDays;
            this.optionalName=optionalName;
            this.end=end;
            this.bottom=bottom;
        }
    }

    //市场添加建筑
    public class Industry {
        Planet planet;
        SplitPane industry;
        Separator top;
        Text start;
        //类型
        ComboBox industries;
        //核心
        ComboBox commodities;
        //道具
        ComboBox items;
        Text end;
        Separator bottom;
        public Industry(SplitPane industry,Separator top,Text start,ComboBox industries,ComboBox commodities,ComboBox items,Text end,Separator bottom){
            this.industry=industry;
            this.top=top;
            this.start=start;
            this.industries=industries;
            this.commodities=commodities;
            this.items=items;
            this.end=end;
            this.bottom=bottom;
        }
    }

    //
    public class CustomEntity {
        SplitPane customEntity;
        Separator top;
        Text start;
        //id
        TextField id;
        //name名称
        TextField name;
        //type
        ComboBox type;
        //factionId势力
        TextField factionId;
        //focus轨道焦点
        TextField focus;
        //angle角度
        TextField angle;
        //orbitRadius轨道半径
        TextField orbitRadius;
        //orbitDays轨道天数
        TextField orbitDays;
        Text end;
        Separator bottom;
        public CustomEntity(SplitPane customEntity,Separator top,Text start,TextField id,TextField name,ComboBox type,TextField factionId,TextField focus,TextField angle,TextField orbitRadius,TextField orbitDays,Text end,Separator bottom){
            this.customEntity=customEntity;
            this.top=top;
            this.start=start;
            this.id=id;
            this.name=name;
            this.type=type;
            this.factionId=factionId;
            this.focus=focus;
            this.angle=angle;
            this.orbitRadius=orbitRadius;
            this.orbitDays=orbitDays;
            this.end=end;
            this.bottom=bottom;
        }
    }
    @FXML
    private void initialize() {
        starType.getItems().addAll(
                "nebula_center_old@老年星云",
                "nebula_center_average@中年星云",
                "nebula_center_young@年轻星云",
                "star_neutron@中子星",
                "black_hole@黑洞",
                "star_yellow@黄恒星",
                "star_white@白矮星",
                "star_blue_giant@蓝巨星",
                "star_blue_supergiant@蓝超巨星",
                "star_orange@橙恒星",
                "star_orange_giant@橙巨星",
                "star_red_supergiant@红超巨星",
                "star_red_giant@红巨星",
                "star_red_dwarf@红矮星",
                "star_browndwarf@棕矮星"
        );
        starType.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
                // 当选择框展开时显示的默认文本
                return starType.getPromptText()+":"+object;
            }

            @Override
            public String fromString(String string) {
                // 选择一个选项后，选择框内显示的文本
                return string;
            }
        });
    }

    //new
    @FXML
    protected void newConfig(ActionEvent event) {
        for (Node node:splitPane.getItems()) {
            if (node instanceof TextField) {
                TextField textField = (TextField)node;
                textField.setText("");
            }
            else if (node instanceof ComboBox){
                ComboBox comboBox = (ComboBox)node;
                comboBox.setValue("");
            }
            else if (node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                checkBox.setSelected(false);
            }
        }
        //主容器
        for (Star star:starList) {
            if (star.star!=firstStar) {
                splitPane.getItems().remove(star.star);
            }
            else {
                for (Planet planet:star.planetList) {
                    star.star.getItems().remove(planet.planet);
                }
            }
        }
        for (SplitPane customEntity:customEntitytList) {
            splitPane.getItems().remove(customEntity);
        }
        for (SplitPane asteroidBelt:asteroidBeltList) {
            splitPane.getItems().remove(asteroidBelt);
        }
        starPaneList.clear();
        starPaneList.add(firstStar);
        Star star = new Star(firstStar,firstTop,firstStart,starName,starType,starRadius,starCoronaSize,firstAddPlanet,firstEnd,firstBottom);
        starList.add(star);
        starMap.put(firstAddPlanet,star);
        planetPaneList.clear();
        marketPaneList.clear();
        industryPaneList.clear();
        starMap.clear();
        marketMap.clear();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
    }

    @FXML
    protected void saveConfig(ActionEvent event) {
        saveConfig();
    }
    protected JSONObject saveConfig() {
        JSONObject starSystem = new JSONObject();
        starSystem.put("star",new JSONArray());
        starSystem.put("asteroidBel",new JSONArray());
        starSystem.put("customEntity",new JSONArray());
        for (Node node:splitPane.getItems()) {
            if (node instanceof TextField) {
                TextField textField = (TextField)node;
                starSystem.put(textField.getId(),textField.getText());
            }
            else if (node instanceof ComboBox){
                ComboBox comboBox = (ComboBox)node;
                starSystem.put(comboBox.getId(),comboBox.getValue());
            }
            else if (node instanceof CheckBox){
                CheckBox checkBox = (CheckBox)node;
                starSystem.put(checkBox.getId(),checkBox.isSelected());
            }
            else if (node instanceof SplitPane) {
                SplitPane splitPane = (SplitPane)node;
                if (starPaneList.contains(splitPane)) {
                    JSONObject star = new JSONObject();
                    JSONArray starJSONArray = starSystem.getJSONArray("star");
                    JSONArray plantJSONArray = new JSONArray();
                    for (Node starNode:splitPane.getItems()) {
                        if (starNode instanceof TextField) {
                            TextField textField = (TextField) starNode;
                            star.put(textField.getId(), textField.getText());
                        } else if (starNode instanceof ComboBox) {
                            ComboBox comboBox = (ComboBox) starNode;
                            star.put(comboBox.getId(), comboBox.getValue());
                        }
                        else if (starNode instanceof SplitPane) {
                            //星球
                            SplitPane planetPane = (SplitPane)starNode;
                            JSONObject planet = new JSONObject();
                            JSONArray childPlanetJSONArray = new JSONArray();
                            for (Node planetNode:planetPane.getItems()) {
                                if (planetNode instanceof TextField) {
                                    TextField textField = (TextField) planetNode;
                                    planet.put(textField.getId(), textField.getText());
                                } else if (planetNode instanceof ComboBox) {
                                    ComboBox comboBox = (ComboBox) planetNode;
                                    planet.put(comboBox.getId(), comboBox.getValue());
                                }
                                else if (planetNode instanceof SplitPane) {
                                    SplitPane planetInPane = (SplitPane)planetNode;
                                    //卫星
                                    if (planetPaneList.contains(planetInPane)) {
                                        JSONObject childPlanet = new JSONObject();
                                        for (Node childPlanetNode:planetInPane.getItems()) {
                                            if (childPlanetNode instanceof TextField) {
                                                TextField textField = (TextField) childPlanetNode;
                                                childPlanet.put(textField.getId(), textField.getText());
                                            } else if (childPlanetNode instanceof ComboBox) {
                                                ComboBox comboBox = (ComboBox) childPlanetNode;
                                                childPlanet.put(comboBox.getId(), comboBox.getValue());
                                            }
                                            else if (childPlanetNode instanceof SplitPane) {
                                                //市场
                                                JSONObject market = new JSONObject();
                                                SplitPane marketPane = (SplitPane)childPlanetNode;
                                                for (Node marketNode:marketPane.getItems()) {
                                                    if (marketNode instanceof TextField) {
                                                        TextField textField = (TextField) marketNode;
                                                        market.put(textField.getId(), textField.getText());
                                                    } else if (marketNode instanceof ComboBox) {
                                                        ComboBox comboBox = (ComboBox) marketNode;
                                                        market.put(comboBox.getId(), comboBox.getValue());
                                                    }
                                                    else if (marketNode instanceof SplitPane) {
                                                        SplitPane marketChildPane = (SplitPane)marketNode;
                                                        for (Node marketChildNode:marketChildPane.getItems()) {
                                                            if (marketChildNode instanceof CheckBox) {
                                                                CheckBox checkBox = (CheckBox) marketChildNode;
                                                                market.put(checkBox.getId(), checkBox.isSelected());
                                                            }
                                                            else if (marketChildNode instanceof ComboBox) {
                                                                ComboBox comboBox = (ComboBox) marketChildNode;
                                                                market.put(comboBox.getId(), comboBox.getValue());
                                                            }
                                                        }
                                                    }
                                                    else if (marketNode instanceof CheckBox) {
                                                        CheckBox checkBox = (CheckBox) marketNode;
                                                        market.put(checkBox.getId(), checkBox.isSelected());
                                                    }
                                                }
                                                childPlanet.put("market",market);
                                            }
                                        }
                                        childPlanetJSONArray.put(childPlanet);
                                    }
                                    else if (marketPaneList.contains(planetInPane)) {
                                        //市场
                                        JSONObject market = new JSONObject();
                                        SplitPane marketPane = planetInPane;
                                        for (Node marketNode:marketPane.getItems()) {
                                            if (marketNode instanceof TextField) {
                                                TextField textField = (TextField) marketNode;
                                                market.put(textField.getId(), textField.getText());
                                            } else if (marketNode instanceof ComboBox) {
                                                ComboBox comboBox = (ComboBox) marketNode;
                                                market.put(comboBox.getId(), comboBox.getValue());
                                            }
                                            else if (marketNode instanceof SplitPane) {
                                                SplitPane marketChildPane = (SplitPane)marketNode;
                                                for (Node marketChildNode:marketChildPane.getItems()) {
                                                    if (marketChildNode instanceof CheckBox) {
                                                        CheckBox checkBox = (CheckBox) marketChildNode;
                                                        market.put(checkBox.getId(), checkBox.isSelected());
                                                    }
                                                    else if (marketChildNode instanceof ComboBox) {
                                                        ComboBox comboBox = (ComboBox) marketChildNode;
                                                        market.put(comboBox.getId(), comboBox.getValue());
                                                    }
                                                }
                                            }
                                            else if (marketNode instanceof CheckBox) {
                                                CheckBox checkBox = (CheckBox) marketNode;
                                                market.put(checkBox.getId(), checkBox.isSelected());
                                            }
                                        }
                                        planet.put("market",market);
                                    }
                                    planet.put("planet",childPlanetJSONArray);
                                }
                            }
                            plantJSONArray.put(planet);
                        }
                    }
                    star.put("planet",plantJSONArray);
                    starJSONArray.put(star);
                    starSystem.put("star", starJSONArray);
                }
                else if (asteroidBeltList.contains(splitPane)) {
                    JSONObject asteroidBel = new JSONObject();
                    JSONArray asteroidBelJSONArray = starSystem.getJSONArray("asteroidBel");
                    for (Node starNode:splitPane.getItems()) {
                        if (starNode instanceof TextField) {
                            TextField textField = (TextField) starNode;
                            asteroidBel.put(textField.getId(), textField.getText());
                        } else if (starNode instanceof ComboBox) {
                            ComboBox comboBox = (ComboBox) starNode;
                            asteroidBel.put(comboBox.getId(), comboBox.getValue());
                        }
                    }
                    asteroidBelJSONArray.put(asteroidBel);
                    starSystem.put("asteroidBelt",asteroidBelJSONArray);
                }
                else if (customEntitytList.contains(splitPane)) {
                    JSONObject customEntity = new JSONObject();
                    JSONArray customEntityJSONArray = starSystem.getJSONArray("customEntity");
                    for (Node starNode:splitPane.getItems()) {
                        if (starNode instanceof TextField) {
                            TextField textField = (TextField) starNode;
                            customEntity.put(textField.getId(), textField.getText());
                        } else if (starNode instanceof ComboBox) {
                            ComboBox comboBox = (ComboBox) starNode;
                            customEntity.put(comboBox.getId(), comboBox.getValue());
                        }
                    }
                    customEntityJSONArray.put(customEntity);
                    starSystem.put("customEntity",customEntityJSONArray);
                }
                else {
                    JSONObject star = new JSONObject();
                    JSONArray starJSONArray = starSystem.getJSONArray("star");
                    JSONArray plantJSONArray = new JSONArray();
                    for (Node starNode:splitPane.getItems()) {
                        if (starNode instanceof TextField) {
                            TextField textField = (TextField) starNode;
                            star.put(textField.getId(), textField.getText());
                        } else if (starNode instanceof ComboBox) {
                            ComboBox comboBox = (ComboBox) starNode;
                            star.put(comboBox.getId(), comboBox.getValue());
                        }
                        else if (starNode instanceof SplitPane) {
                            //星球
                            SplitPane planetPane = (SplitPane)starNode;
                            JSONObject planet = new JSONObject();
                            JSONArray childPlanetJSONArray = new JSONArray();
                            for (Node planetNode:planetPane.getItems()) {
                                if (planetNode instanceof TextField) {
                                    TextField textField = (TextField) planetNode;
                                    planet.put(textField.getId(), textField.getText());
                                } else if (planetNode instanceof ComboBox) {
                                    ComboBox comboBox = (ComboBox) planetNode;
                                    planet.put(comboBox.getId(), comboBox.getValue());
                                }
                                else if (planetNode instanceof SplitPane) {
                                    SplitPane planetInPane = (SplitPane)planetNode;
                                    //卫星
                                    if (planetPaneList.contains(planetInPane)) {
                                        JSONObject childPlanet = new JSONObject();
                                        for (Node childPlanetNode:planetInPane.getItems()) {
                                            if (childPlanetNode instanceof TextField) {
                                                TextField textField = (TextField) childPlanetNode;
                                                childPlanet.put(textField.getId(), textField.getText());
                                            } else if (childPlanetNode instanceof ComboBox) {
                                                ComboBox comboBox = (ComboBox) childPlanetNode;
                                                childPlanet.put(comboBox.getId(), comboBox.getValue());
                                            }
                                            else if (childPlanetNode instanceof SplitPane) {
                                                //市场
                                                JSONObject market = new JSONObject();
                                                JSONArray industryJSONArray = new JSONArray();
                                                SplitPane marketPane = (SplitPane)childPlanetNode;
                                                for (Node marketNode:marketPane.getItems()) {
                                                    if (marketNode instanceof TextField) {
                                                        TextField textField = (TextField) marketNode;
                                                        market.put(textField.getId(), textField.getText());
                                                    } else if (marketNode instanceof ComboBox) {
                                                        ComboBox comboBox = (ComboBox) marketNode;
                                                        market.put(comboBox.getId(), comboBox.getValue());
                                                    } else if (marketNode instanceof CheckBox) {
                                                        CheckBox checkBox = (CheckBox) marketNode;
                                                        market.put(checkBox.getId(), checkBox.isSelected());
                                                    } else if (marketNode instanceof SplitPane) {
                                                        SplitPane marketInPane = (SplitPane)marketNode;
                                                        if (industryPaneList.contains(marketInPane)) {
                                                            JSONObject industry = new JSONObject();
                                                            for (Node marketInNode : marketInPane.getItems()) {
                                                                if (marketInNode instanceof ComboBox) {
                                                                    ComboBox comboBox = (ComboBox) marketInNode;
                                                                    industry.put(comboBox.getId(), comboBox.getValue());
                                                                }
                                                            }
                                                            industryJSONArray.put(industry);
                                                        }
                                                        else {
                                                            //基本选项
                                                            for (Node marketInNode : marketInPane.getItems()) {
                                                                if (marketInNode instanceof CheckBox) {
                                                                    CheckBox checkBox = (CheckBox) marketInNode;
                                                                    market.put(checkBox.getId(), checkBox.isSelected());
                                                                } else if (marketInNode instanceof ComboBox) {
                                                                    ComboBox comboBox = (ComboBox) marketInNode;
                                                                    market.put(comboBox.getId(), comboBox.getValue());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                market.put("industry",industryJSONArray);
                                                childPlanet.put("market",market);
                                            }
                                        }
                                        childPlanetJSONArray.put(childPlanet);
                                    }
                                    else if (marketPaneList.contains(planetInPane)) {
                                        //市场
                                        JSONObject market = new JSONObject();
                                        JSONArray industryJSONArray = new JSONArray();
                                        SplitPane marketPane = (SplitPane)planetInPane;
                                        for (Node marketNode:marketPane.getItems()) {
                                            if (marketNode instanceof TextField) {
                                                TextField textField = (TextField) marketNode;
                                                market.put(textField.getId(), textField.getText());
                                            } else if (marketNode instanceof ComboBox) {
                                                ComboBox comboBox = (ComboBox) marketNode;
                                                market.put(comboBox.getId(), comboBox.getValue());
                                            } else if (marketNode instanceof CheckBox) {
                                                CheckBox checkBox = (CheckBox) marketNode;
                                                market.put(checkBox.getId(), checkBox.isSelected());
                                            } else if (marketNode instanceof SplitPane) {
                                                SplitPane marketInPane = (SplitPane)marketNode;
                                                if (industryPaneList.contains(marketInPane)) {
                                                    JSONObject industry = new JSONObject();
                                                    for (Node marketInNode : marketInPane.getItems()) {
                                                        if (marketInNode instanceof ComboBox) {
                                                            ComboBox comboBox = (ComboBox) marketInNode;
                                                            industry.put(comboBox.getId(), comboBox.getValue());
                                                        }
                                                    }
                                                    industryJSONArray.put(industry);
                                                }
                                                else {
                                                    //基本选项
                                                    for (Node marketInNode : marketInPane.getItems()) {
                                                        if (marketInNode instanceof CheckBox) {
                                                            CheckBox checkBox = (CheckBox) marketInNode;
                                                            market.put(checkBox.getId(), checkBox.isSelected());
                                                        } else if (marketInNode instanceof ComboBox) {
                                                            ComboBox comboBox = (ComboBox) marketInNode;
                                                            market.put(comboBox.getId(), comboBox.getValue());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        market.put("industry",industryJSONArray);
                                        planet.put("market",market);
                                    }
                                    planet.put("planet",childPlanetJSONArray);
                                }
                            }
                            plantJSONArray.put(planet);
                        }
                    }
                    star.put("planet",plantJSONArray);
                    starJSONArray.put(star);
                    starSystem.put("star", starJSONArray);
                }
            }
        }

        String currentDirectory = System.getProperty("user.dir");
        System.out.println("当前程序所在目录：" + currentDirectory);
        FileWriter fr;
        String fileName = name.getText()+System.currentTimeMillis()+".json";
        try {
            fr = new FileWriter(fileName);
            fr.write("");
            fr.flush();
            fr.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            Files.write(FileSystems.getDefault().getPath(fileName),
                    starSystem.toString().getBytes(Charset.forName("utf-8")), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(starSystem.toString());
        System.out.println(starSystem.toString());
        alert.showAndWait();
        return starSystem;
    }
    @FXML
    protected void generateCode(ActionEvent event) {
        saveConfig();
    }

    @FXML
    protected void openConfig(ActionEvent event) {
        newConfig(event);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开配置文件");

        // 显示文件选择对话框
        File file = fileChooser.showOpenDialog(vBox.getScene().getWindow());
        if (file != null) {
            try {
                // 读取文件内容
                byte[] fileContent = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
                // 处理文件内容
                System.out.println(new String(fileContent));
                try {
                    JSONObject starSystemJSONObject = new JSONObject(new String(fileContent));
                    //星系
                    if (!starSystemJSONObject.isNull("star")){
                        JSONArray starJSONArray = starSystemJSONObject.getJSONArray("star");
                        for (int starJSONArrayindex = 0;starJSONArrayindex<starJSONArray.length();starJSONArrayindex++) {
                            if (starJSONArrayindex>0) {
                                addStar(addStar);
                            }
                            SplitPane starPane = starPaneList.get(starJSONArrayindex);
                            //赋值
                            JSONObject starJSONObect = starJSONArray.getJSONObject(starJSONArrayindex);
                            if (!starJSONObect.isNull("planet")){
                                Button addPlanet = starList.get(starJSONArrayindex).addPlanet;
                                setPlanet(addPlanet,starJSONObect,0);
                            }
                            //星系本体
                            setValue(starPane,starJSONObect);
                        }
                    }
                    if (!starSystemJSONObject.isNull("customEntity")){
                        JSONArray customEntityJSONArray = starSystemJSONObject.getJSONArray("customEntity");
                        if (customEntityJSONArray.length()>0) {
                            for (int customEntityJSONArrayindex = 0;customEntityJSONArrayindex<customEntityJSONArray.length();customEntityJSONArrayindex++) {
                                CustomEntity customEntity = addCustomEntity(addCustomEntity);
                                setValue(customEntity.customEntity,customEntityJSONArray.getJSONObject(customEntityJSONArrayindex));
                            }
                        }
                    }
                    if (!starSystemJSONObject.isNull("asteroidBelt")){
                        JSONArray asteroidBeltJSONArray = starSystemJSONObject.getJSONArray("asteroidBelt");
                        if (asteroidBeltJSONArray.length()>0) {
                            for (int asteroidBeltJSONArrayindex = 0;asteroidBeltJSONArrayindex<asteroidBeltJSONArray.length();asteroidBeltJSONArrayindex++) {
                                AsteroidBelt asteroidBelt = addAsteroidBelt(addAsteroidBelt);
                                setValue(asteroidBelt.asteroidBelt,asteroidBeltJSONArray.getJSONObject(asteroidBeltJSONArrayindex));
                            }
                        }
                    }
                    setValue(splitPane,starSystemJSONObject);
                }
                catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setValue(SplitPane industry, JSONObject jsonObject) {
        for (Node node:industry.getItems()) {
            if (!jsonObject.isNull(node.getId())) {
                if (node instanceof TextField) {
                    TextField textField = (TextField) node;
                    textField.setText(jsonObject.getString(node.getId()));
                } else if (node instanceof ComboBox) {
                    ComboBox comboBox = (ComboBox) node;
                    comboBox.setValue(jsonObject.getString(node.getId()));
                } else if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    checkBox.setSelected(jsonObject.getBoolean(node.getId()));
                }
            }
        }
    }

    //Button addPlanet = starList.get(starJSONArrayindex).addPlanet;
    private void setPlanet(Button addPlanet,JSONObject starJSONObect,int index) {
        JSONArray planetJSONArray = starJSONObect.getJSONArray("planet");
        for (int planetJSONArrayindex = 0;planetJSONArrayindex<planetJSONArray.length();planetJSONArrayindex++) {
            Planet planet = null;
            if (index>0) {
                planet = addPlanetNoElse(addPlanet);
            }
            else {
                planet = addPlanet(addPlanet);
            }
            JSONObject planetJSONObject = planetJSONArray.getJSONObject(planetJSONArrayindex);
            setValue(planet.planet,planetJSONObject);
            if (!planetJSONObject.isNull("planet")){
                setPlanet(planet.addPlanet,planetJSONObject,index+1);
            }
            if (!planetJSONObject.isNull("market")){
                JSONObject marketJSONObject = planetJSONObject.getJSONObject("market");
                Market market = addMarket(planet.addMarket);
                if (!marketJSONObject.isNull("industry")){
                    JSONArray industryJSONArray = marketJSONObject.getJSONArray("industry");
                    for (int industryJSONArrayindex = 0;industryJSONArrayindex<industryJSONArray.length();industryJSONArrayindex++) {
                        Industry industry = addIndustry(market.industries);
                        setValue(industry.industry,industryJSONArray.getJSONObject(industryJSONArrayindex));
                    }
                }
                SplitPane marketPane = market.market;
                for (Node node:marketPane.getItems()) {
                    if (node instanceof TextField) {
                        TextField textField = (TextField) node;
                        if (!marketJSONObject.isNull(node.getId())) {
                            textField.setText(marketJSONObject.getString(node.getId()));
                        }
                    } else if (node instanceof ComboBox) {
                        ComboBox comboBox = (ComboBox) node;
                        if (!marketJSONObject.isNull(node.getId())) {
                            comboBox.setValue(marketJSONObject.getString(node.getId()));
                        }
                    } else if (node instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) node;
                        if (!marketJSONObject.isNull(node.getId())) {
                            checkBox.setSelected(marketJSONObject.getBoolean(node.getId()));
                        }
                    } else if (node instanceof SplitPane) {
                        SplitPane marketInPane = (SplitPane) node;
                        setValue(marketInPane, marketJSONObject);
                    }
                }
            }
        }
    }
}
