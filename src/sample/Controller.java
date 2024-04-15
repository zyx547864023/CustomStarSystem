package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private VBox vBox;
    @FXML
    private Button addStar;
    @FXML
    private Button addPlanet;
    @FXML
    private SplitPane splitPane;

    @FXML
    SplitPane firstStar;
    @FXML
    Separator firstTop;
    @FXML
    Text firstStart;
    @FXML
    TextField firstStarName;
    @FXML
    ComboBox firstStarType;
    @FXML
    TextField firstStarRadius;
    @FXML
    TextField firstStarCoronaSize;
    @FXML
    Button firstAddPlanet;
    @FXML
    Text firstEnd;
    @FXML
    Separator firstBottom;

    private List<Star> starList = new ArrayList<Star>();

    private Map<Button,Star> starMap = new HashMap<>();
    private Map<Button,Planet> planetMap = new HashMap<>();

    @FXML
    protected void addStar(ActionEvent event) {
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
                starName.setPromptText("请输入主星名称");
                starPane.getItems().add(starName);
                //主星类型
                ComboBox starType = new ComboBox();
                starType.setPromptText("请输入主星类型");
                starPane.getItems().add(starType);
                //主星范围
                TextField starRadius = new TextField();
                starRadius.setPromptText("请输入主星范围");
                starPane.getItems().add(starRadius);
                //日冕范围
                TextField starCoronaSize = new TextField();
                starCoronaSize.setPromptText("请输入日冕范围");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("实在是不会写所以用alert刷新布局");
                alert.showAndWait();
            }
            else {
            }
        }
        else {

        }
    }
    @FXML
    protected void addPlanet(ActionEvent event) {
            addPlanet((Button) event.getSource());
    }

    protected void addPlanet(Button addPlanet) {
        Star star = starMap.get(addPlanet);
        int starIndex = starList.indexOf(star)+1;
        int planetIndex = 0;
        if (star!=null) {
            planetIndex = star.planetList.size();
        }
        else {
            star = new Star(firstStar,firstTop,firstStart,firstStarName,firstStarType,firstStarRadius,firstStarCoronaSize,addPlanet,firstEnd,firstBottom);
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
        TextField planetName = new TextField();
        planetName.setPromptText("请输入行星名称");
        planetPane.getItems().add(planetName);
        //主星类型
        ComboBox planetType = new ComboBox();
        planetType.setPromptText("请输入行星类型");
        planetPane.getItems().add(planetType);
        //添加星球按钮
        Button addChildPlanet = new Button();
        addChildPlanet.setText("为行星添加一颗卫星");
        addChildPlanet.setOnAction(e -> {
            addPlanetNoElse(addChildPlanet);
        });
        planetPane.getItems().add(addChildPlanet);
        Text end = new Text();
        end.setText("主星"+starIndex+"行星"+planetIndex+"结束");
        planetPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: BLUE;");
        planetPane.getItems().add(bottom);
        //starList.get(index).getItems().add(starList.get(index).getItems().indexOf(addPlanetList.get(index)),planetPane);
        starMap.get(addPlanet).star.getItems().add(starMap.get(addPlanet).star.getItems().indexOf(addPlanet),planetPane);
        Planet planet = new Planet(planetPane,top,start,planetName,planetType,null,null,addChildPlanet,end,bottom);
        star.planetList.add(planet);
        planetMap.put(addChildPlanet,planet);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
    }

    protected void addPlanetNoElse(Button addPlanet) {
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
        TextField planetName = new TextField();
        planetName.setPromptText("请输入行星名称");
        planetPane.getItems().add(planetName);
        //主星类型
        ComboBox planetType = new ComboBox();
        planetType.setPromptText("请输入行星类型");
        planetPane.getItems().add(planetType);
        Text end = new Text();
        end.setText("行星"+planetIndex+"卫星"+planet.planetList.size()+"结束");
        planetPane.getItems().add(end);
        Separator bottom = new Separator();
        bottom.setStyle("-fx-background-color: GREEN;");
        planetPane.getItems().add(bottom);
        //starList.get(index).getItems().add(starList.get(index).getItems().indexOf(addPlanetList.get(index)),planetPane);
        planetMap.get(addPlanet).planet.getItems().add(planetMap.get(addPlanet).planet.getItems().indexOf(addPlanet),planetPane);
        Planet childPlanet = new Planet(planetPane,top,start,planetName,planetType,null,null,null,end,bottom);
        planet.planetList.add(childPlanet);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("实在是不会写所以用alert刷新布局");
        alert.showAndWait();
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
        TextField planetName;
        ComboBox planetType;
        TextField planetRadius;
        TextField planetCoronaSize;
        Button addPlanet;
        Text end;
        Separator bottom;
        List<Planet> planetList = new ArrayList<>();
        public Planet(SplitPane planet,Separator top,Text start,TextField planetName,ComboBox planetType,TextField planetRadius,TextField planetCoronaSize,Button addPlanet,Text end,Separator bottom){
            this.planet=planet;
            this.top=top;
            this.start=start;
            this.planetName=planetName;
            this.planetType=planetType;
            this.planetRadius=planetRadius;
            this.planetCoronaSize=planetCoronaSize;
            this.addPlanet=addPlanet;
            this.end=end;
            this.bottom=bottom;
        }
    }
}
