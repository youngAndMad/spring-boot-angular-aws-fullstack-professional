import {Component, OnInit} from '@angular/core';
import {FileService} from "../../service/file.service";
import {User} from "../../model/User";
import {UserService} from "../../service/user.service";
import {SessionService} from "../../service/session.service";
import {AmazonFile} from "../../model/AmazonFile";

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {

  file: File
  user: User

  constructor(
    private fileService: FileService,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    document.getElementById('closePopup')!.addEventListener('click', () => {
      document.getElementById('popup')!.style.display = 'none'
    })
    let localStorageUser = JSON.parse(localStorage.getItem('user')!)
    this.userService.findById(localStorageUser.id)
      .subscribe(userResponse => {
        this.user =  userResponse
        localStorage.setItem('user' , JSON.stringify(userResponse))
      })
  }

  openUploadFileModal() {
    document.getElementById('popup')!.style.display = 'block'
  }

  onFileInputChange(event: any) {
    this.file = event.target.files[0]
  }

  uploadFile() {
    this.fileService.uploadFile(this.file,this.user.id!,false)
      .subscribe((res) => alert(res))
  }

  downloadFile(amazonFile:AmazonFile){
    this.fileService.downloadDile(amazonFile.name)
      .subscribe()
  }


}
