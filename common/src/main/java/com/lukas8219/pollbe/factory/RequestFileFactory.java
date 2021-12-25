package com.lukas8219.pollbe.factory;

import com.lukas8219.pollbe.data.interfaces.RequestFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class RequestFileFactory {

    private RequestFileFactory() {
    }

    ;

    public static RequestFile of(MultipartFile file) throws IOException {
        return new RequestFileImpl(file);
    }


    private static class RequestFileImpl implements RequestFile {
        private final byte[] bytes;
        private final String originalFileName;

        private RequestFileImpl(MultipartFile file) throws IOException {
            this.bytes = file.getBytes();
            this.originalFileName = file.getOriginalFilename();
        }

        @Override
        public byte[] getBytes() {
            return bytes;
        }

        @Override
        public String getOriginalFileName() {
            return originalFileName;
        }

        @Override
        public String getExtension() {
            var idx = originalFileName.lastIndexOf(".") + 1;
            return originalFileName.substring(idx);
        }
    }
}
