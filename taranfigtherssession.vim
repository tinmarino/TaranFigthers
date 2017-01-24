if &cp | set nocp | endif
let s:cpo_save=&cpo
set cpo&vim
inoremap <C-Space> 
inoremap <expr> <Up> pumvisible() ? "\" : "\<Up>"
inoremap <expr> <S-Tab> pumvisible() ? "\" : "\<S-Tab>"
inoremap <expr> <Down> pumvisible() ? "\" : "\<Down>"
imap <Nul> <C-Space>
inoremap <S-Down> :let tmp=getpos('.') :m+1 : call cursor(tmp[1]+1,tmp[2]) a
inoremap <S-Up> :let tmp=getpos('.') :m-2 : call cursor(tmp[1]-1,tmp[2]) a
vnoremap  "+p
nnoremap  "+p
nnoremap  :update
vnoremap  :update
onoremap  :update
vnoremap  "+y
nnoremap  "+y
vnoremap   zf
nnoremap   za
nnoremap ,d :YcmShowDetailedDiagnostic
nmap ,j <Plug>(CommandTJump)
nmap ,t <Plug>(CommandT)
map ,0 :10b
map ,9 :9b
map ,8 :8b
map ,7 :7b
map ,6 :6b
map ,5 :5b
map ,4 :4b
map ,3 :3b
map ,2 :2b
map ,1 :1b
map ,g :e#
map ,f :bn
map ,b :bp
map ,l :ls
nnoremap ,ev :vsplit $MYVIMRC
noremap <silent> ,cu :silent s/^\V=escape(b:comment_leader,'\/')//e:nohlsearch
noremap <silent> ,cc :silent s/^/=escape(b:comment_leader,'\/')/:nohlsearch
map ,u :s,^\(\s*\)[^# \t]\@=// ,\1,gv
map ,c :s,^\(\s*\)[^# \t]\@=,\1// ,gv
vnoremap < <gv 
vnoremap > >gv 
nnoremap H :set cursorline! cursorcolumn! 
map [1;2C <S-Right>
map [1;2D <S-Left>
map [1;5C <C-Right>
map [1;5D <C-Left>
map [1;5B <C-Down>
map [1;5A <C-Up>
vmap gx <Plug>NetrwBrowseXVis
nmap gx <Plug>NetrwBrowseX
map xy :s/x/y/g
map yx :s/y/x/g
map z9 :set foldlevel=9
map z8 :set foldlevel=8
map z7 :set foldlevel=7
map z6 :set foldlevel=6
map z5 :set foldlevel=5
map z4 :set foldlevel=4
map z3 :set foldlevel=3
map z2 :set foldlevel=2
map z1 :set foldlevel=1
map z0 :set foldlevel=0
vnoremap <silent> <Plug>NetrwBrowseXVis :call netrw#BrowseXVis()
nnoremap <silent> <Plug>NetrwBrowseX :call netrw#NetrwBrowseX(expand("<cWORD>"),0)
nnoremap <SNR>63_: :=v:count ? v:count : ''
nnoremap <silent> <Plug>(CommandTJump) :CommandTJump
nnoremap <silent> <Plug>(CommandTBuffer) :CommandTBuffer
nnoremap <silent> <Plug>(CommandT) :CommandT
nnoremap <F10> :b 
noremap <C-Tab> :BufExplorer
vnoremap <silent> <C-Down> zM
nnoremap <silent> <C-Down> zM
vnoremap <silent> <C-Up> zR
nnoremap <silent> <C-Up> zR
vnoremap <silent> <C-Left> zm
nnoremap <silent> <C-Left> zm
vnoremap <silent> <C-Right> zr
nnoremap <silent> <C-Right> zr
vnoremap <S-Down> :m '>+1gv
vnoremap <S-Up> :m '<-2gv
nnoremap <S-Up> :let tmp=getpos('.') :m-2 : call cursor(tmp[1]-1,tmp[2]) 
nnoremap <S-Down> :let tmp=getpos('.') :m+1 : call cursor(tmp[1]+1,tmp[2]) 
inoremap  yiW<End>==0
inoremap <expr> 	 pumvisible() ? "\" : "\	"
inoremap  :update
cnoremap %% =expand('%:p:h')
cmap [1;2C <S-Right>
cmap [1;2D <S-Left>
imap jk 
imap kj 
let &cpo=s:cpo_save
unlet s:cpo_save
set autoindent
set autoread
set background=dark
set backspace=2
set backupdir=~/.vim/backup//
set balloonexpr=eclim#util#Balloon(eclim#util#GetLineError(line('.')))
set completefunc=youcompleteme#Complete
set completeopt=preview,menuone
set cpoptions=aAceFsB
set directory=~/.vim/backup//
set fileencodings=ucs-bom,utf-8,default,latin1
set foldlevelstart=30
set helplang=en
set history=10000
set hlsearch
set incsearch
set mouse=a
set path=~/Software/Java/Libgdx/Jar/Source/libgdx-nightly-20160329/sources
set printoptions=paper:a4
set ruler
set runtimepath=~/.vim,~/.vim/bundle/L9,~/.vim/bundle/Vundle.vim,~/.vim/bundle/YouCompleteMe,~/.vim/bundle/command-t,~/.vim/bundle/nerdtree,~/.vim/bundle/python-mode,~/.vim/bundle/sparkup,~/.vim/bundle/tabular,~/.vim/bundle/vim-colorscheme-switcher,~/.vim/bundle/vim-fugitive,~/.vim/bundle/vim-misc,~/.vim/bundle/vis,/var/lib/vim/addons,/usr/share/vim/vimfiles,/usr/share/vim/vim74,/usr/share/vim/vimfiles/after,/var/lib/vim/addons/after,~/.vim/bundle/python-mode/after,~/.vim/after,~/.vim/eclim,~/.vim/eclim/after
set shiftwidth=4
set smartcase
set suffixes=.bak,~,.swp,.o,.info,.aux,.log,.dvi,.bbl,.blg,.brf,.cb,.ind,.idx,.ilg,.inx,.out,.toc,.class
set noswapfile
set tabstop=4
set title
set undodir=~/.vim/undo
set undofile
set undolevels=100000
set updatetime=2000
set wildcharm=26
set wildmenu
set nowritebackup
" vim: set ft=vim :
