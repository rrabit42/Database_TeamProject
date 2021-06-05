package gui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import dao.MovieDao;
import dao.ReviewDao;
import models.Movies;
import models.Reviews;

@SuppressWarnings("serial")
public class Movie extends CustomUI {

    private JFrame frame = new JFrame();
    // 세로 스크롤 사용, 가로 스크롤 사용 안함
    private JPanel container = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JPanel backgroundPanel;
    private JLabel lbIcon, lbTitle, lbTitleMovie, lbTItleRating, lbTitleGenreAge, lbTitleRunOpenTime, lbTitleDirector, lbTitleActor;
    private JLabel lbMovie, lbRating, lbGenreAge, lbCountry, lbRunOpenTime, lbDirector, lbActor;

    private JButton btnMain, btnBack;

    private String nickname;

    public Movie(String title) {
        this.nickname = nickname;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();

        MovieDao mDao = MovieDao.getInstance();
//        Movies movie = mDao.selectTitle(title);
//
//        // 라벨에 값들 set하기
//        lbMovie.setText(movie.getMovie());
//
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        lbDate.setText(dateFormat.format(review.getCreate_time()));
//        lbDate.setText(review.getCreate_time());
//        lbRating.setText(Float.toString(review.getRating()));
//        lbDetail.setText(review.getDetail());

        btnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Main(nickname);
                frame.dispose();
            }
        });

        // 이전 페이지로 돌아가도록(수정)
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnCd = JOptionPane.showConfirmDialog(frame, "메인 페이지로 돌아가시겠습니까?", "경고", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(returnCd == JOptionPane.YES_OPTION) {
                    new Main(nickname);
                    frame.dispose();
                }
            }
        });

        frame.add(container);
        frame.setSize(426, 800);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void init() {
        backgroundPanel = new JPanel();
        frame.setContentPane(backgroundPanel);
        frame.setTitle("영화 예매 프로그램 ver1.0");

        CustomUI custom = new CustomUI(backgroundPanel);
        custom.setPanel();

        lbIcon = custom.setLbImg("lbIcon", 4, 160, 130);
        lbMovie = custom.setLb("lbTitle", "영화제목", 100, 150, 220, 185, "center", 20, "bold");

        lbTItleRating = custom.setLb("lbTitleMovie", "평점", 35, 310, 150, 20, "left", 17, "bold");
        lbRating = custom.setLb("lbMovie", "해당 영화의 평점입니다.", 195, 310, 180, 20, "right", 17, "plain");

        lbTitleGenreAge = custom.setLb("lbTitleDate", "장르/연령제한", 35, 360, 150, 20, "left", 17, "bold");
        lbGenreAge = custom.setLb("lbDate", "장르와 연령제한입니다.", 195, 360, 180, 20, "right", 17, "plain");

        lbCountry = custom.setLb("lbTitleDate", "나라", 35, 410, 150, 20, "left", 17, "bold");
        lbCountry = custom.setLb("lbDate", "나라입니다.", 195, 410, 180, 20, "right", 17, "plain");

        lbTitleRunOpenTime = custom.setLb("lbTitleRating", "상영시간/개봉일", 35, 460, 150, 20, "left", 17, "bold");
        lbRunOpenTime = custom.setLb("lbRating", "상연시간과 개봉일입니다.", 195, 460, 180, 20, "right", 17, "plain");

        lbTitleDirector = custom.setLb("lbTitleDetail", "감독", 35, 510, 150, 20, "left", 17, "bold");
        lbDirector = custom.setLb("lbDetail", "영화를 맡은 감독입니다.", 195, 510, 180, 20, "right", 17, "plain");

        lbTitleActor = custom.setLb("lbTitleDetail", "출연배우", 35, 560, 150, 20, "left", 17, "bold");
        lbActor = custom.setLb("lbDetail", "출연배우들 입니다.", 195, 560, 180, 20, "right", 17, "plain");


        btnMain = custom.setBtnBlue("btnMain", "메인으로", 635);
        btnBack = custom.setBtnWhite("btnBack", "이전으로", 695);
    }
}