import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-new-products',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,
    FormsModule],
  templateUrl: './new-products.component.html',
  styleUrl: './new-products.component.css'
})
export class NewProductsComponent implements OnInit{

  productForm!: FormGroup

  constructor(private formBuilder: FormBuilder, private productService: ProductService) {
  }
  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: this.formBuilder.control('',[Validators.required, Validators.minLength(4)]),
      price: this.formBuilder.control(0),
      checked: this.formBuilder.control(false),

    })
  }

  saveProduct() {
        this.productService.addProduct(this.productForm.value)
          .subscribe({
            next : data => alert(JSON.stringify(data)),
            error : err => console.log(err)
          })
  }

  getErrorMessage(name: string, errors: ValidationErrors | null) {
    return "";
  }
}
