import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../services/product.service";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators} from "@angular/forms";
import {CommonModule} from "@angular/common";
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-product',
  standalone: true,
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent implements OnInit{
  productId! : number
  productForm!: FormGroup
  constructor(private formBuilder: FormBuilder,private activatedRoute : ActivatedRoute,private productService: ProductService,private router: Router) {
  }
  ngOnInit(): void {
    this.productId = this.activatedRoute.snapshot.params['id']
    this.productService.getProductById(this.productId).subscribe({
      next: product => {
        this.productForm = this.formBuilder.group({
          id: this.formBuilder.control(this.productId),
          name: this.formBuilder.control(product.name,[Validators.required, Validators.minLength(4)]),
          price: this.formBuilder.control(product.price),
          checked: this.formBuilder.control(product.checked),

        })
      },
      error : err => console.log(err)
    })
  }

  getErrorMessage(name: string, errors: ValidationErrors | null) {
    return "";
  }

  updateProduct() {
    this.productService.updateProduct(this.productForm.value)
      .subscribe({
        next : data => {
          alert(JSON.stringify(data))
          this.router.navigateByUrl(`/products`);
        },
        error : err => console.log(err)
      })  }
}
