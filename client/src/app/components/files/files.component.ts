import {Component, OnInit} from '@angular/core';
import {FileService} from "../../service/file.service";
import {User} from "../../model/User";

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit{

  loading: boolean = false
  file: File
  user:User

  constructor(
    private fileService: FileService
  ) {}

  ngOnInit(): void {
    document.getElementById('closePopup')!.addEventListener('click', () => {
      document.getElementById('popup')!.style.display = 'none';
    });
    this.user = JSON.parse(localStorage.getItem('user')!)
  }

  openUploadFileModal() {
    document.getElementById('popup')!.style.display = 'block';
  }

  onFileInputChange(event: any) {
    this.file = event.target.files[0];
  }

  uploadFile() {
    this.loading = !this.loading;
    this.fileService.uploadFile(
      this.file,
      this.user.id!,
      false
    ).subscribe(console.log)
  }
}
