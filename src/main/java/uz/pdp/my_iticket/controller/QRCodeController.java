package uz.pdp.my_iticket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.my_iticket.service.qrCodeService.QRCodeServiceImpl;

@RestController
@RequestMapping("/api")
public class QRCodeController {

        private static final int WIDTH = 300;
        private static final int HEIGHT = 300;

        @Autowired
        private QRCodeServiceImpl qrCodeService;

        @GetMapping("/generate-qr-code")
        public ResponseEntity<?> getQrCode() {
            String contentToGenerateQrCode = "Simple Solution";
            byte[] qrCode = qrCodeService.generateQRCode(contentToGenerateQrCode, WIDTH, HEIGHT);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+"</////>"+"\"")
                    .body(qrCode);
        }

}
