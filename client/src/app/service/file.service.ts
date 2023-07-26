import {HttpClient} from '@angular/common/http';
import {Injectable, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {API} from "../utils/config";

@Injectable({
  providedIn: 'root',
})
export class FileService {

  constructor(
    private http: HttpClient
  ) {
  }


  uploadFile(
    file: File,
    userId: number,
    isAvatar: boolean
  ): Observable<any> {
    const formData = new FormData();

    formData.append("file", file, file.name);
    formData.append("fileName", isAvatar ? "avatar" : file.name);
    formData.append("id", `${userId}`);

    return this.http
      .post(`${API}/storage/save`, formData)
  }

  downloadDile(
    fileName: string
  ): Observable<any> {
    return this.http.get(`${API}/storage/download/${fileName}`,
      {
        responseType: 'blob'
      }
    )
  }


}
