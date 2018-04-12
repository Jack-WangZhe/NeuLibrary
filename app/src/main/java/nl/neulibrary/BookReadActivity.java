package nl.neulibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import tools.removeTitle;

public class BookReadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView bookName;
    private ImageView sectionButton;
    private TextView bookSectionTitle;
    private TextView bookSectionContent;
    private ImageView back;
    private ImageView config;

    private String book_name;
    private String book_id;
    private String book_section_title;
    private String book_section_content;

    private String[] section;
    private ListView sectionRadioListView;
    private RadioOnClick radioOnClick = new RadioOnClick(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitle.remove(this);
        setContentView(R.layout.activity_book_read);
        getIntentInfo();
        initViews();
    }

    //获取传过来的数据信息
    public void getIntentInfo() {
        Intent book = getIntent();
        book_id = book.getStringExtra("bookId");
        book_name = book.getStringExtra("bookName");
        //发送volley请求，获取id的对应第一章内容

        //模拟得到内容为:
        book_section_title = "第一章 后街的日本人";
        book_section_content = "\t\t我生在日本占领北平时期，而且后街（也称后河沿）住的都是日本人，但不是占领军，而是一些小商人、小职员之类。后河沿的房子显然比西河沿的差，一律极小的院子，大约五六间房，低矮潮湿。有些人是在“七七事变”之前就在这里住了，多是朝出晚归，很少与中国人来往。唯有双方的孩子有点交往，但多是打架。中国小孩多，往往占上风，但日本孩子吃了亏好像很少向家长哭诉，也不见日本大人拉着他们孩子来找中国家长的；偶尔中国小孩被日本小孩打了，中国家长也不敢找日本家长说理，这使得中国人很愤懑。\n" +
                "\t\t虽然日本投降时我才三岁，但对日本占领还是留下些恐怖印象的。自生下来就有一种怪病，年年（主要是1943—1945）一到夏秋之际就泻肚，这是“虎列拉”（霍乱）的典型症状。而日本鬼子是“谈‘虎’色变”，搞了一个“活埋‘虎列拉’”的政策，这使父母一直惴惴不安，唯恐大祸临头，害怕我的病被日本人知道了，为此不敢让我出门，好容易等到日本投降了，病也好了。日本统治的后几年里，太平洋战争越打越糟，许多物质被日本人征用，北平缺吃少喝，山西老家寄来点儿大枣、葡萄，一进北平火车站（站在前门），日本人就打“六六六”消毒，喷得全白了，弄得谁也不敢吃了，只好扔掉。另一件震动北平的大事是“逮麻子”，有位中国“地工”人员暗杀了日本一个军政大员，传说这个“地工”是个“麻子”，于是北平的“麻子”倒霉了，都不敢上街，弄得人人自危，恐怖阴云笼罩全城。\n" +
                "\t\t西西河沿一带居民最反感的是后街日本人的生活习惯。上面说过后街日本人住的院子极狭小，日本男人特别爱洗身子，不论冬夏他们脱得赤条条的，只用一根布条子兜裆，在街上大洗特洗，这让中国人很不习惯，甚至是痛心疾首。街坊聚在一起就爱骂日本人“洗澡”这件事。“日本人真不是个东西，光天化日，竟敢赤身露体！”“谁家没有大姑娘小媳妇，真是禽兽！”所以中国人很少走后街，即使从街口过，也是目不斜视。\n" +
                "\t\t1945年抗战胜利，后街的日本人悄无声息地搬走了，前街的居民去了一块心病。日本人走了，我们家也起了点变化，买了164号院，成了有房一族。这房子的原主人是东北人，姓司，夫妇两人带着一个小姑娘过活，是日本翻译官，似乎没有什么劣迹，只是日本人走了，他失业了，要回老家，住房出售。那时北京房子便宜得让今人难以想象（近百年来北京房价六起六落），164号是个三合院，北屋三间半，东、西屋各两间，没有南屋，但院子大，约有二十多平米，北房与东、西房之间各有夹道，可盖小房。就这样一个院子售价仅为一百匹本色粗白布（最便宜的布）。当时一匹为十丈，按现在价格也就是二三百元，那么，一百匹合现在的两三万元。买了这个小院后，粗装修（主要是把室内地面改为水泥地，纸窗户改为玻璃窗，墙壁用可赛银粉刷）花了20匹布。给我留的印象最深的是司家即将搬走时，给我们留下一副网球拍子，这是我小时唯一的、带有点洋气的玩具。";
    }

    public void initViews() {
        bookName = (TextView) findViewById(R.id.bookName);
        bookName.setText(book_name);
        sectionButton = (ImageView) findViewById(R.id.sectionButton);
        sectionButton.setOnClickListener(new RadioClickListener());
        bookSectionTitle = (TextView) findViewById(R.id.bookSectionTitle);
        bookSectionTitle.setText(book_section_title);
        bookSectionContent = (TextView) findViewById(R.id.bookSectionContent);
        bookSectionContent.setText(book_section_content);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        config = (ImageView) findViewById(R.id.config);
        config.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.config:
                Intent toConfig = new Intent(this, selfSiteActivity.class);
                startActivity(toConfig);
                break;
        }
    }

    /**
     * 单选弹出菜单窗口
     */
    class RadioClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //发送volley请求获取所有章节标题
            //模拟章节标题内容
            String[]info = new String[]{"第一章 后街的日本人","第二章 幼儿园","第三章 祭灶——过年的前奏曲"};
            int length = info.length;
            section = new String[length];
            for(int i=0;i<info.length;i++){
                section[i] = info[i];
            }
            AlertDialog ad = new AlertDialog.Builder(BookReadActivity.this).setTitle("请选择章节")
                    .setSingleChoiceItems(section, radioOnClick.getIndex(), radioOnClick).create();
            sectionRadioListView = ad.getListView();
            ad.show();
        }
    }

    /**
     * 点击单选框事件
     */
    class RadioOnClick implements DialogInterface.OnClickListener {
        private int index;

        public RadioOnClick(int index) {
            this.index = index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public void onClick(DialogInterface dialog, int whichButton){
            setIndex(whichButton);
            //发送请求获取章节文章内容
            //section[index]
            //Toast.makeText(BookReadActivity.this, "您已经选择了： " + index + ":" + section[index], Toast.LENGTH_LONG).show();

            //模拟返回数据
            book_section_title = section[index];
            book_section_content = "\t\t那时幼儿园还很少，我三四岁时，上了永光寺幼儿园，这是北平南城相当不错的幼儿园。在幼儿园中记得最清楚的有两件事，一是吴佩孚大帅（北平人简称为“吴大帅”）有个孙辈的小孩与我同班；另一件事是分发美国援华食品及衣物。食品是美国军用罐头，外包装呈绿色，有猪肉、牛肉、奶粉三种，每桶有三四斤，大约每人发了十几桶，小孩拿不动，是用洋车拉回家的。衣服是卷成包袱状，编号摊在地面，同学抓阄，我希望能抓到一套运动装，但抓到的却是一件白底儿细碎绿花、精布连衣裙，十分遗憾。听说有的同学抓到的衣服兜里还有美元，老师说，衣物都是街头募捐来的，有人在街上把衣服脱了下来，交给募捐人，所以常常在衣兜里发现零钱。\n" +
                    "\t\t那时独门独院的家里都不愿意孩子到门外去玩，总感到不安全，这是日本统治的后遗症。母亲常常告诫：不要跑出去和“野孩子”玩！在她心目中，凡是常在街上玩的小孩都是“野孩子”，这样使我与街坊的孩子自然有了隔阂。日本人在时，家里就不许出门，在我心中，大门以外，是个神秘世界，值得去冒一冒险，于是常常偷跑出去。外面还真是有风险，北平围城时（深秋），气氛很紧张，到处是麻袋（里面装黄土）做成的掩体与荷枪实弹的士兵。我和一个小孩，从马道跑上城墙，疯跑、疯玩，在凛冽的秋风中摘取从城墙砖缝中滋长出的酸枣刺上的残余酸枣。此时一个穿着黑色警察服、背着三八式步枪的人把我们喝住了，举着枪说要毙了我们。当然，这是他利用那点小小的权力逞逞威风，吓一吓对他毫无危险的孩子，俩小孩都吓哭了。他满足了，又去管一个登城的大人，我们俩分两个方向跑下城墙，不停脚地一直跑回了家，庆幸“死里逃生”，有好几天不敢出门。\n" +
                    "\t\t老不出门，显得很不合群，有点孤单。与我们隔三个门有家煤铺，煤铺掌柜的孩子最多，有三四个，他们自成一伙，也是街上的孩子王。有时我也想加入他们一伙，被拒绝了。其中老大说：那不成，你拿铅笔来，我们才跟你玩；你拿馒头来，我们给你讲故事。我曾用铅笔和馒头换得这一切，但我总弄不清楚，为什么馒头的功用如此短暂（讲一个故事只五分钟），而铅笔却能维持半天呢？\n" +
                    "\t\t20世纪40年代末，北平仅仅有80万人，街上行人稀稀疏疏，宽一点儿的街道的两旁，夏天青草离离，秋天草一黄就显得很荒凉，到了下午四五点钟，像西西河沿这样的街上就没有行人了。但时时有算命的盲人，或敲着“报君知”的小锣，或吹着单调的笛子（1—2—3，3—2—1）从门前走过，更显得凄凉。";
            bookSectionTitle.setText(book_section_title);
            bookSectionContent.setText(book_section_content);
            dialog.dismiss();
        }
    }
}
