package com.nettube.search.image.downloader.service;


import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.net.URL;

@Service
public interface DownloaderService {

    BufferedImage download(String url);

    BufferedImage download(URL url);

}
