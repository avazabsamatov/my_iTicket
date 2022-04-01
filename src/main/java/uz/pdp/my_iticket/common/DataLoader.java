package uz.pdp.my_iticket.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.my_iticket.enums.CastType;
import uz.pdp.my_iticket.enums.MovieStatus;
import uz.pdp.my_iticket.model.*;
import uz.pdp.my_iticket.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    CastRepository castRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    DistributorRepository distributorRepository;
    @Autowired
    ExtraTimeBetweenSessionsRepository extraTimeBetweenSessionsRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieAnnouncementRepository movieAnnouncementRepository;
    @Autowired
    MovieSessionRepository movieSessionRepository;
    @Autowired
    PayTypeRepository payTypeRepository;
    @Autowired
    PriceCategoryRepository priceCategoryRepository;
    @Autowired
    RowRepository repository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SessionAdditionalFeeInPercentRepository sessionAdditionalFeeInPercentRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        //Save User
        User user = userRepository.save(new User(
                "Avaz",
                "Absamatov",
                "avaz1999",
                "19991811",
                "avazabsamatov4@gmail.com",
                "+998936279955"));
        //Save Attachment
        List<Attachment> attachments = new ArrayList<>(Arrays.asList(
                new Attachment("C:\\Users\\HP\\Desktop\\Router Scan 2.60","img.jpg",10000L),
                new Attachment("C:\\Users\\HP\\Desktop\\Router Scan 2.60","svg.jpg",10000L),
                new Attachment("C:\\Users\\HP\\Desktop\\Router Scan 2.60","png.jpg",10000L)
        ));

        List<Attachment> attachmentList = attachmentRepository.saveAll(attachments);
        System.out.println("Attachment size "+attachmentList.size());
        System.out.println("Attachment get(0) "+attachmentList.get(0));


        //Save AttachmentContent
        AttachmentContent attachmentContent = attachmentContentRepository.save(new AttachmentContent(
                null,
                attachments.get(0)
        ));
        //Price Categories
        List<PriceCategory> priceCategories = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            priceCategories.add(new PriceCategory(
                    "category" + (i + 1), (2.0 * i), (i + i + i + i)
            ));
        }
        priceCategoryRepository.saveAll(priceCategories);

        List<Hall> hallList = new ArrayList<>(Arrays.asList(
                new Hall("zal1",0.0),
                new Hall("zal2",0.0),
                new Hall("zal3",0.0),
                new Hall("zal_vip",10.0)
        ));
        List<Hall> halls = hallRepository.saveAll(hallList);

        //Row and seats save in database
        List<Row> rows = new ArrayList<>();
        List<Seat> seats  = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rows.add(new Row((i+1),hallList.get(0)));
            for (int j = 0; j < 10; j++) {
                seats.add(new Seat((i+1),priceCategories.get(0),rows.get(i)));
            }
        }
        repository.saveAll(rows);
        seatRepository.saveAll(seats);

        List<Row> rows2 = new ArrayList<>();
        List<Seat> seats2  = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rows2.add(new Row((i+1),hallList.get(1)));
            for (int j = 0; j < 10; j++) {
                seats2.add(new Seat((i+1),priceCategories.get(1),rows2.get(i)));
            }
        }
        repository.saveAll(rows2);
        seatRepository.saveAll(seats2);


        //Casts save in DB
        List<Cast> casts = new ArrayList<>(Arrays.asList(
                new Cast("Avazbek",attachmentList.get(0), CastType.CAST_ACTOR),
                new Cast("Jamshid",attachmentList.get(1), CastType.CAST_OTHER),
                new Cast("Alibek",attachmentList.get(2), CastType.CAST_DIRECTOR)
        ));
        List<Cast> castList = castRepository.saveAll(casts);

        List<Genre> genres = new ArrayList<>(Arrays.asList(
                new Genre("Melodrama"),
                new Genre("Detective"),
                new Genre("Fantastic")
        ));
        List<Genre> genreList = genreRepository.saveAll(genres);

        List<Distributor> distributors =  new ArrayList<>(Arrays.asList(
                new Distributor("Shoxrux","Hayotda faqat yaxshilik qil",attachments.get(0)),
                new Distributor("Komron","Hayotda faqat yaxshilik qil",attachments.get(0))
        ));
        List<Distributor> distributorList = distributorRepository.saveAll(distributors);

        List<Movie> movies = new ArrayList<>(Arrays.asList(
                new Movie(
                        "Hulk",
                        "Wery good",
                        90, 50000.0,
                        "youtube.com",
                        LocalDate.now(),
                        1000000.0,
                        MovieStatus.MOVIE_ACTIVE,
                        20.0,
                        attachmentList.get(0),
                        castList,
                        genreList,
                        null,
                        distributorList),
                new Movie(
                        "Spiderman",
                        "Hello how are you",
                        129,
                        60000.0,
                        "youtube.com",
                        LocalDate.now(),
                        2000000.0,
                        MovieStatus.MOVIE_ACTIVE,
                        40.0,
                        attachmentList.get(1),
                        castList,
                        genreList,
                        null,
                        distributorList)
        ));

        List<Movie> movieList = movieRepository.saveAll(movies);

        List<MovieAnnouncement> movieAnnouncements = new ArrayList<>(Arrays.asList(
                new MovieAnnouncement(movieList.get(0),true),
                new MovieAnnouncement(movieList.get(1),true)
        ));

        List<MovieAnnouncement> movieAnnouncementList = movieAnnouncementRepository.saveAll(movieAnnouncements);

        List<MovieSession> movieSessions = new ArrayList<>(Arrays.asList(
                new MovieSession(hallList.get(0),
                        LocalDate.of(2022,4,12),
                        LocalTime.of(15,00),
                        LocalTime.of(17,00),
                        10.0,
                        movieAnnouncementList.get(0)),
                new MovieSession(hallList.get(1),
                        LocalDate.of(2022,5,10),
                        LocalTime.of(10,00),
                        LocalTime.of(13,00),
                        20.0,
                        movieAnnouncementList.get(1))
        ));
        List<MovieSession> movieSessionList = movieSessionRepository.saveAll(movieSessions);


    }
}
